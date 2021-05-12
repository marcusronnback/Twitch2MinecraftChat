This is a fork of https://github.com/axel012/ircbot, thank you Axel012.
# Twitch2MinecraftChat
Twitch2MinecraftChat is a twitch chat bot plugin for Minecraft Bukkit/Spigot servers. Based on the ircbot by axel012.
  - Displays twitch chat ingame using the Minecraft Chat.

# Minecraft Commands
  - /start or /tmc:start - Starts Twitch Bot
  - /stop or /tmc:stop - Stop Twitch Bot
  - /togglechat - Enable/Disable Twitch chat in game

# Config.yml
When running the plugin for the first time, this config file will be generated, in plugins/Ircbot/config.yml
Follow the instructions below to configure your Config file.
```yaml
# Enable/Disable twitch chat in minecraft chat
# Timeout is the time between a user message in milliseconds (to prevent spam)
chat:
  enable: true
  timeout: 250
  prefix: '[Twitch]'
# channel: channel to connect to
# username: twitch credentials username
# oauth_pwd: oauth string generated from https://twitchapps.com/tmi/,
twitch:
  channel: ''
  username: ''
  oauth_pwd: ''
```

### Build
- Clone repository
- From IDE
  Open in intellij or eclipse, run gradle build task
- From command line
  gradlew build

Plugin .jar will be in build/libs if build compeletes successfully.


### Thanks 
  - Thanks to Paul Mutton for its Twitch library http://www.jibble.org/pircbot.php
  - Thanks to Axel012 for his ircbot that this is heavily based upon.

### Todos
 - Save user colors etc. Clean up chat.
 - Move some customization to config.
