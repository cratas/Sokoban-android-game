package com.vsb.kru13.sokoban;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


/**
 * Created by kru13 on 12.10.16.
 */
public class SokoView extends View{

    Bitmap[] bmp;

    int lx;
    int ly;

    int width;
    int height;

    int xHero = 6;
    int yHero = 4;

    float xClickedPosition = 0;
    float yClickedPosition = 0;
    float xUnClickedPosition = 0;
    float yUnClickedPosition = 0;

    int currentLevel = 0;

    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;

//    private int level[] = {
//            1,1,1,1,1,1,1,1,1,0,
//            1,0,0,0,0,0,0,0,1,0,
//            1,0,2,3,3,2,0,0,1,0,
//            1,0,0,3,2,3,2,0,1,0,
//            1,0,2,3,3,2,4,0,1,0,
//            1,0,0,3,2,3,2,0,1,0,
//            1,0,2,3,3,2,0,0,1,0,
//            1,0,0,0,0,0,0,0,1,0,
//            1,1,1,1,1,1,1,1,1,0,
//            0,0,0,0,0,0,0,0,0,0
//    };
    private int allLevels[][];
    private int level[];
    private int levelMemory[];
//    private int level[] = {
//            1,1,1,1,1,1,1,1,1,0,
//            1,0,0,0,0,0,0,0,1,0,
//            1,0,2,3,3,2,1,0,1,0,
//            1,0,1,3,2,3,2,0,1,0,
//            1,0,2,3,3,2,4,0,1,0,
//            1,0,1,3,2,3,2,0,1,0,
//            1,0,2,3,3,2,1,0,1,0,
//            1,0,0,0,0,0,0,0,1,0,
//            1,1,1,1,1,1,1,1,1,0,
//            0,0,0,0,0,0,0,0,0,0
//    };

//    private int levelMemory[] = {
//            1,1,1,1,1,1,1,1,1,0,
//            1,0,0,0,0,0,0,0,1,0,
//            1,0,0,3,3,0,1,0,1,0,
//            1,0,1,3,0,3,0,0,1,0,
//            1,0,0,3,3,0,0,0,1,0,
//            1,0,1,3,0,3,0,0,1,0,
//            1,0,0,3,3,0,1,0,1,0,
//            1,0,0,0,0,0,0,0,1,0,
//            1,1,1,1,1,1,1,1,1,0,
//            0,0,0,0,0,0,0,0,0,0
//    };



    private static final int EMPTY = 0;
    private static final int WALL = 1;
    private static final int BOX = 2;
    private static final int GOAL = 3;
    private static final int PLAYER = 4;
    private static final int GOAL_BOX = 5;

    public SokoView(Context context) {
        super(context);
        init(context);
    }

    public SokoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SokoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {
        bmp = new Bitmap[6];

        bmp[EMPTY] = BitmapFactory.decodeResource(getResources(), R.drawable.empty);
        bmp[WALL] = BitmapFactory.decodeResource(getResources(), R.drawable.wall);
        bmp[BOX] = BitmapFactory.decodeResource(getResources(), R.drawable.box);
        bmp[GOAL] = BitmapFactory.decodeResource(getResources(), R.drawable.goal);
        bmp[PLAYER] = BitmapFactory.decodeResource(getResources(), R.drawable.hero);
        bmp[GOAL_BOX] = BitmapFactory.decodeResource(getResources(), R.drawable.boxok);
    }

    public void setAllLevels(int[][] levels, int width, int height) {
        lx = height;
        ly = width;

        allLevels = new int[levels.length][];
        for(int i = 0; i < levels.length; i++)
        {
            int[] tmpArray = levels[i];
            int arrLength = tmpArray.length;
            allLevels[i] = new int[arrLength];
            System.arraycopy(tmpArray, 0, allLevels[i], 0, arrLength);
        }
    }

    public void setLevel(int[] newLevel, int lvlNumber) {
        currentLevel = lvlNumber;
        for (int i = 0; i < lx; i++) {
            for (int j = 0; j < ly; j++) {
                if(newLevel[i*ly + j] == 4) {
                    xHero = j;
                    yHero = i;
                    break;
                }
            }
        }

        level = new int[newLevel.length];
        levelMemory = new int[newLevel.length];

        System.arraycopy(newLevel, 0, level, 0, newLevel.length);
        System.arraycopy(newLevel, 0, levelMemory, 0, newLevel.length);

        for(int i = 0; i < newLevel.length;i++) {
            if(levelMemory[i] == 2 || levelMemory[i] == 4) {
                levelMemory[i] = 0;
            }
            if(levelMemory[i] == 5) {
                levelMemory[i] = 3;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            {
                xClickedPosition = event.getX();
                yClickedPosition = event.getY();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                xUnClickedPosition = event.getX();
                yUnClickedPosition = event.getY();

                float yDifference = Math.abs(yUnClickedPosition - yClickedPosition);
                float xDifference = Math.abs(xUnClickedPosition - xClickedPosition);

                if(xDifference > yDifference) {
                    if(xUnClickedPosition > xClickedPosition){
                        moveRight();
                    } else {
                        moveLeft();
                    }
                } else if(xDifference < yDifference) {
                    if(yUnClickedPosition > yClickedPosition){
                        moveDown();
                    } else {
                        moveUp();
                    }
                } else {
                    return true;
                }
            }
        }
        return true;
    }

    private void moveRight() {
        if (level[yHero * ly + (xHero + 1)] == WALL) {
            return;
        } else if (level[yHero * ly + (xHero + 1)] == BOX || level[yHero * ly + (xHero + 1)] == GOAL_BOX) {
            if (level[yHero * ly + (xHero + 2)] == BOX || level[yHero * ly + (xHero + 2)] == WALL  || level[yHero * ly + (xHero + 2)] == GOAL_BOX) {
                return;
            } else {
                level[yHero * ly + (xHero + 1)] = PLAYER;
                if(level[yHero * ly + (xHero+2)] == GOAL) {
                    level[yHero * ly + (xHero+2)] = GOAL_BOX;
                } else if(level[yHero * ly + (xHero+2)] == GOAL_BOX) {
                    level[yHero * ly + (xHero+2)] = BOX;
                }
                else {
                    level[yHero * ly + (xHero+2)] = BOX;
                }
            }
        }

        level[yHero * ly + xHero] = levelMemory[yHero * ly + xHero];
        level[yHero * ly + ++xHero] = PLAYER;
        invalidate();
        winnerCheck();
    }

    private void moveLeft() {
        if(level[yHero * ly + (xHero-1)] == WALL) {
            return;
        } else if (level[yHero * ly + (xHero-1)] == BOX || level[yHero * ly + (xHero-1)] == GOAL_BOX) {
            if(level[yHero * ly + (xHero-2)] == BOX || level[yHero * ly + (xHero-2)] == WALL || level[yHero * ly + (xHero-2)] == GOAL_BOX) {
                return;
            } else {
                level[yHero * ly + (xHero-1)] = PLAYER;
                if(level[yHero * ly + (xHero-2)] == GOAL) {
                    level[yHero * ly + (xHero-2)] = GOAL_BOX;
                } else if(level[yHero * ly + (xHero-2)] == GOAL_BOX) {
                    level[yHero * ly + (xHero-2)] = BOX;
                }
                else {
                    level[yHero * ly + (xHero-2)] = BOX;
                }
            }
        }

        level[yHero * ly + xHero] = levelMemory[yHero * ly + xHero];
        level[yHero * ly + --xHero] = PLAYER;
        invalidate();
        winnerCheck();
    }

    private void moveUp() {
        if(level[(yHero -1) * ly + xHero] == WALL) {
            return;
        } else if (level[(yHero-1) * ly + xHero] == BOX || level[(yHero-1) * ly + xHero] == GOAL_BOX) {
            if (level[(yHero - 2) * ly + xHero] == BOX || level[(yHero - 2) * ly + xHero] == WALL || level[(yHero - 2) * ly + xHero] == GOAL_BOX) {
                return;
            } else {
                level[(yHero - 1) * ly + xHero] = PLAYER;
                if(level[(yHero-2) * ly + xHero] == GOAL) {
                    level[(yHero-2) * ly + xHero] = GOAL_BOX;
                } else if(level[(yHero-2) * ly + xHero] == GOAL_BOX) {
                    level[(yHero-2) * ly + xHero] = BOX;
                }
                else {
                    level[(yHero-2) * ly + xHero] = BOX;
                }
            }
        }

        level[yHero * ly + xHero] = levelMemory[yHero * ly + xHero];
        level[--yHero * ly + xHero] = PLAYER;
        invalidate();
        winnerCheck();
    }

    private void moveDown() {
        if(level[(yHero +1) * ly + xHero] == WALL) {
            return;
        } else if (level[(yHero+1) * ly + xHero] == BOX  || level[(yHero+1) * ly + xHero] == GOAL_BOX) {
            if (level[(yHero + 2) * ly + xHero] == BOX || level[(yHero + 2) * ly + xHero] == WALL || level[(yHero + 2) * ly + xHero] == GOAL_BOX) {
                return;
            } else {
                level[(yHero+1) * ly + xHero] = PLAYER;
                if(level[(yHero+2) * ly + xHero] == GOAL) {
                    level[(yHero+2) * ly + xHero] = GOAL_BOX;
                } else if(level[(yHero+2) * ly + xHero] == GOAL_BOX) {
                    level[(yHero+2) * ly + xHero] = BOX;
                }
                else {
                    level[(yHero+2) * ly + xHero] = BOX;
                }
            }
        }

        level[yHero * ly + xHero] = levelMemory[yHero * ly + xHero];
        level[++yHero * ly + xHero] = PLAYER;
        invalidate();
        winnerCheck();
    }

    void winnerCheck() {
        boolean boxExists = false;
        for(int x : level) {
            if(x == BOX) {
                boxExists = true;
            }
        }

        if(!boxExists) {
            if(currentLevel < 5) {
                setLevel(allLevels[++currentLevel], currentLevel);
            } else {
                Toast.makeText(getContext(), "YOU WON!", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w / ly;
        height = h / lx;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < lx; i++) {
            for (int j = 0; j < ly; j++) {
                canvas.drawBitmap(bmp[level[i*ly + j]], null,
                        new Rect(j*width, i*height,(j+1)*width, (i+1)*height), null);
            }
        }

    }
}
