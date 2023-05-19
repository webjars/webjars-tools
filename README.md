Create Exec:
```
./gradlew linkReleaseExecutable
```

Run:
```
build/bin/linuxX64/releaseExecutable/webjars-tools.kexe YOUR_FILE
```

On NixOS:
```
NIXPKGS_ALLOW_UNFREE=1 nix-shell -p '(steam.override { extraLibraries = (pkgs: [ pkgs.openssl ]); }).run'
steam-run build/bin/linuxX64/releaseExecutable/webjars-tools.kexe YOUR_FILE
```

Dev:
```
./gradlew -t runDebugExecutable -PrunArgs="YOUR_FILE"
```
