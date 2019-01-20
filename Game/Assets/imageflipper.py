import cv2
for i in range(0,8):
    imageSource = 'C:/Users/nizar/Documents/SimpleGame/Game/Assets/SPRITES/Megaman/Zero/Jump/'+str(i)+'.png'
    img = cv2.imread(imageSource)
    hi=cv2.flip(img,1)
    cv2.imwrite('C:/Users/nizar/Documents/SimpleGame/Game/Assets/SPRITES/Megaman/Zero/JumpL/'+str(i)+'.png', hi)


