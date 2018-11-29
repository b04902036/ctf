#!/usr/bin/python3
import numpy as np
import cv2 as cv
import itertools as it
import qrtools
import os

ans_img = None

#ans_range = [5,6,16/25,16/25,2,15,26,3]

ans = [5, 6, 16, 25, 2, 15, 26, 3, 23, 10, 20, 19, 21, 7, 8, 1, 22, 4, 24, 18, 14]
print (len(set(ans)))
# ans[15], ans[19]
# ans[2], ans[3]
# ans[16], ans[17], ans[18]
# ans[10], ans[12]
# ans[9], ans[11]
for set1 in it.permutations([ans[15], ans[19]]):
    ans[15], ans[19] = set1
    for set2 in it.permutations([ans[2], ans[3]]):
        ans[2], ans[3] = set2
        for set3 in it.permutations([ans[16], ans[17], ans[18]]):
            ans[16], ans[17], ans[18] = set3
            for set4 in it.permutations([ans[10], ans[12]]):
                ans[10], ans[12] = set4
                for set5 in it.permutations([ans[9], ans[11]]):
                    ans[9], ans[11] = set5
                    #for set6 in it.permutations([])
                    img = cv.imread('0.png')
                    ans_img = cv.imread('0.png')
                    for i in range(2):
                        ans_img = np.append(ans_img, img, 1)
                    for i in ans:
                        name = str(i) + '.png'
                        img = cv.imread(name)
                        if ans_img is None:
                            ans_img = img
                        else:
                            ans_img = np.append(ans_img, img, 1)
                    for i in range(3):
                        img = cv.imread('0.png')
                        ans_img = np.append(ans_img, img, 1)
                    cv.imwrite('ans.png', ans_img)

                    os.system('./decode.py')
os.system('./check.py')
