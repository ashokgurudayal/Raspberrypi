from tkinter import * 
import socket 
import pyttsx3
from pygame import mixer
import os

def refresher():
    print('refresher invoked')
    host = '127.0.0.1'
    port = 5000
    s = socket.socket()
    s.bind((host,port))
    print('Listening on port---',port)
    s.listen(1)
    c, addr = s.accept()
    print ("Connection from: " + str(addr))
    while True:
        mixer.init() # initialize the mixer inorder to play audio
        data = c.recv(5) # Receive the command text for the server to get ready to display text,play audio or do text to speech conversion
        if not data:
            break
        data = data.decode('utf-8')
        print (data)
        if data == 'Text0':
            mixer.music.stop() #Stop playing any audio if it is currently playing
            data = c.recv(1024)
            print('Got the text message'+str(data))
            explanation.set(data)
        elif data == 'Audio':
            mixer.music.stop()
            mixer.music.load('test1.mp3')
            explanation.set("Receiving File")
            
            f = open('test.mp3','wb')
            l = c.recv(4096)
            while (l):
                explanation.set("R")
                print ("Recieving...")
                f.write(l)
                l = c.recv(4096)
            f.close()
            explanation.set("Audio message Received..Playing Audio")
            #mixer.init()
            mixer.music.load('test.mp3')
            mixer.music.play()
            #mixer.music.stop()
        elif data == 'Stop0':
            mixer.music.stop()
            mixer.music.load('test1.mp3')
            os.remove('test.mp3')
        elif data == 'Txosp':
            mixer.music.stop() #Stop playing any audio if it is currently playing
            data = c.recv(1024)
            data = data.decode('utf-8')
            engine = pyttsx3.init()
            engine.say(data)
            engine.setProperty('volume',0.9)
            engine.runAndWait()
            explanation.set("Received text and converted to Audio...")
            pass
    c.close()
    root.after(1000,refresher) 

'''To initialize Tkinter, we have to 
create a Tk root widget, which is a window with a title bar and other 
decoration provided by the window manager. The root widget has to be 
created before any other widgets and there can only be one root 
widget.''' 

root = Tk() 
root.attributes('-fullscreen',True)
#root.state('zoomed')
'''The next line of code contains the Label widget. The first parameter 
of the Label call is the name of the parent window, in our case "root". 
So our Label widget is a child of the root widget. The keyword parameter 
"text" specifies the text to be shown:'''
#w = Label(root, text="Hello Tkinter!")
screen_width = root.winfo_screenwidth() 
explanation = StringVar()
#w2 = Label(root,justify=CENTER,padx = 10, 
#text=explanation).pack(side="left")
msg = Message(root, textvar = explanation,width=screen_width) 
#explanation.set('gdfgdfgdfgdfgdfdgdgdfg') 
msg.config(bg='lightgreen',padx="50",justify="center",font=('times', 24, 'italic')) 
msg.pack(side="left")
#w2.pack( ) The pack method tells Tk to fit the size of the window to 
#the given text. w.pack()
explanation.set('Waiting to receive command') 
root.after(3000,refresher)
#The window won't appear until we enter the Tkinter event loop:
root.mainloop()
#root.iconify()

