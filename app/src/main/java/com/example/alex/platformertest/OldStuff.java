package com.example.alex.platformertest;

public class OldStuff {


    /*
            vertices = new float[(b.blockWidth+1)*(b.blockHeight+1)*2];
            indices = new short[(b.blockWidth+1)*2*b.blockHeight+(b.blockHeight-1)*2];

            setVertices(b);
            setIndices(b);
    */


    /*
    private void setVertices(Block b) {
        for (int i = 0; i < b.blockHeight+1; i++) {
            for (int j = 0; j < b.blockWidth+1; j++) {
                vertices[i*j+j*2] = b.blockX+64*j;
                vertices[i*j+j*2+1] = 720-40-(b.blockY+64*i);
            }
        }
    }

    private void setIndices(Block b) {
        for (int i = 0; i < b.blockHeight; i++) {
            for (int j = 0; j < b.blockWidth*2+2; j++) {
                if (j % 2 == 1) {
                    indices[j+i*(b.blockWidth*2+2)+(i*2)] = (short)(j/2+(b.blockWidth+1)*(i+1));
                } else {
                    indices[j+i*(b.blockWidth*2+2)+(i*2)] = (short)(j/2 + i*(b.blockWidth+1));
                }
            }
            if (b.blockHeight > 1 && i < b.blockHeight-1) {
                indices[(i+1)*(b.blockWidth*2+2)+(i*2)]   = (short)((i+1)*(b.blockWidth+1)+b.blockWidth);
                indices[(i+1)*(b.blockWidth*2+2)+(i*2)+1] = (short)((i+1)*(b.blockWidth+1));
            }
        }
    }
    */




}
