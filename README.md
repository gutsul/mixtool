# mixtool

### 0. Requirements
- Linux-based OS
- Installed ffmpeg tool
- Installed java

### 1. Documentation

Key       | Description                                | Value 
----------|--------------------------------------------|-------
 `-asrc`  | *Audio source*. The absolute file path to which you want to apply effects.| /path/to/source/file.wav 
 `-d`     | *Duration*. Create silent file with duration of N seconds.     |   60 
 `-se`    | *Sound effect*. The absolute file paths to sound effects you want to apply.     |    /sound_efects/ahem_x.wav <br>/sound_efects/bee.wav 
 `-t`     | *Time line*. Time in milliseconds when to apply the effect. If adding tag 'L' effect will be loops.    |    1000 or 1000L 
 `-out`   | *Output*. The absolute file path where you want to save mixed file.     |    /path/to/output/file.wav 

**Impotant:** <br>
1. You can choose only `-asrc` or `-d` key. <br>
2. Number of effects and time stamps in time line should be the same. <br>

### 2. Usage examples
##### Mix 3 sound effects with source file
```
java -jar mixtool.jar -asrc /home/user/source.wav \
-se /sound_efects/ahem_x.wav \
/sound_efects/ahem_x.wav \
/sound_efects/ahem_x.wav \
-t 0 5000L 18000 \
-out /home/ygrigortsevich/Documents/SpilnaSprava/ffmpeg/output/filename.wav
```
*What command do:* <br>
1. Audio source file is */home/user/source.wav*<br>
2. Uses three */sound_efects/ahem_x.wav* effect.<br>
3. Effects applies to 0, 5, 18 seconds. On the fifth second effect repeats.<br>
4. Mixed file saved in */home/ygrigortsevich/Documents/SpilnaSprava/ffmpeg/output/filename.wav*<br>

##### Mix 3 sound effects with silent file.
```
java -jar mixtool.jar -d 60 \
-se /sound_efects/ahem_x.wav \
/sound_efects/ahem_x.wav \
/sound_efects/ahem_x.wav \
-t 0 5000L 18000 \
-out /home/ygrigortsevich/Documents/SpilnaSprava/ffmpeg/output/filename.wav
```
*What command do:*<br>
1. Create silent audio file with duration of 60 seconds <br>
2. Uses three */sound_efects/ahem_x.wav* effect. <br>
3. Effects applies to 0, 5, 18 seconds. On the fifth second effect repeats. <br>
4. Mixed file saved in */home/ygrigortsevich/Documents/SpilnaSprava/ffmpeg/output/filename.wav*<br>