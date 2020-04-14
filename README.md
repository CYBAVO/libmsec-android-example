# CYBAVO Device Security Example

## Project setup

Edit `local.properties` to config Maven repository URL / credentials provided by CYBAVO

```
cybavo.maven.url=$MAVEN_REPO_URL
cybavo.maven.username=$MAVEN_REPO_USRENAME
cybavo.maven.password=$MAVEN_REPO_PASSWORD
```

## Property references

Property      | Description
--------------|-----------------------------
isJailBroken                 | Is device jail-broken(rooted). i.e. SuperSU
isHooked                     | Is device hooked. i.e. Cydia Substrate, Xposed
isEmulator                   | Is device an emulator. i.e. AVD, BlueStacks
isVirtualApp                 | Is device virtual. i.e. Parallel Space, Multiple Accounts
isMockLocationEnabled        | Is [mock location](https://developer.android.com/studio/debug/dev-options#debugging) enabled
isOnExternalStorage          | Is app installed on [external storage](https://developer.android.com/guide/topics/data/install-location#Should)
isDevelopmentSettingsEnabled | Is [development settings](https://developer.android.com/studio/debug/dev-options) enabled
isDebuggingEnabled           | Is app [debugging](https://developer.android.com/studio/debug)-enabled
isAdbEnabled                 | Is [ADB (Android Debug Bridge)](https://developer.android.com/studio/command-line/adb#Enabling) enabled
