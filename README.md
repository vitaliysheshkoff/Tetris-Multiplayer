# Tetris
 
This project is implemented in Java.

Tetris has audio provided by the [AudioCue](https://github.com/philfrei/AudioCue).

Tetris has several game modes.
Single game and multiplayer game.

# Multiplayer
- [x]  LOCAL –  P2P game over LAN (player and his opponent must be connected to the same LAN)
- [x] VPN –  P2P game by simulating a local network, using, for example, hamachi (two players first create a virtual private network, and then connect in Tetris itself)
- [x] INTERNET –  P2P game using a technology called UDP hole punching and STUN, through which players can be connected to different networks
- [x] WEB – game using the [web server](https://github.com/vitaliysheshkoff/tetris_server), which is an intermediary between two players (The player first creates a room to which his opponent later connects by entering the number of the created room
- [x] TELEGRAM – by pressing the "telegram request" button, the player sends a request for the game to the [telegram bot](https://t.me/tetris_game_tetris_bot), any member of this bot can accept the request. After accepting the request, the Tetris application will be automatically opened. Works only after installation on Windows OS.  (The game is the same as the WEB)
- [ ] AI comming soon...

# Screenshots
 
 ## Menu
 <img src="screenshots/image_2021-12-31_23-44-31.png" width="920" >
 
 ## Play process
 <img src="screenshots/image_2021-12-31_23-27-22.png" width="920" >
 
  ## Leaderboard
 <img src="screenshots/image_2021-12-31_23-20-26.png" width="920" >
 
  ## Settings
 <img src="screenshots/options.png" width="920" >
  
  ## Multiplayer
 <img src="screenshots/multiplayer.png" width="920" >
 
 > waiting for an opponent 
 <img src="screenshots/waiting.png" width="920" >
 
 > playing with an opponent
  <img src="screenshots/image_2022-01-01_00-13-54.png" width="920" >
  
  # Download
- [installer](https://github.com/vitaliysheshkoff/Tetris-Multiplayer/blob/resize_network_multiplayer_branch/out/artifacts/Tetris_Multiplayer_jar/installer/mysetup.exe) for Window OS
- [exe](https://github.com/vitaliysheshkoff/Tetris-Multiplayer/blob/resize_network_multiplayer_branch/out/artifacts/Tetris_Multiplayer_jar/tetris.exe)
- [jar](https://github.com/vitaliysheshkoff/Tetris-Multiplayer/blob/resize_network_multiplayer_branch/out/artifacts/Tetris_Multiplayer_jar/Tetris-Multiplayer.jar)
