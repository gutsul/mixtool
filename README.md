# mixtool

### 0. Requirements
- Linux-based OS
- Installed ffmpeg tool
- Installed java

### 1. Documentation

**Mixtool** може працювати в трьох режимах: <br>
- *ONLY AUDIO* <br>
- *ONLY VIDEO* <br>
- *VIDEO&AUDIO* <br>

#### 1.1 Режим *ONLY AUDIO*

|Key       | Description                                                                 | Value  |  
|:-------- |-----------------------------------------------------------------------------|--------|
| `-asrc`  | *Audio source*. The absolute file path to which you want to apply effects.  | /path/to/source/file.wav 
| `-d`     | *Duration*. Create silent file with duration of N seconds.                  |   60 
| `-se`    | *Sound effect*. The absolute file paths to sound effects you want to apply. |    /sound_efects/ahem_x.wav <br>/sound_efects/bee.wav 
| `-t`     | *Time line*. Time in milliseconds when to apply the effect. If adding tag 'L' effect will be loops.    |    1000 or 1000L 
| `-out`   | *Output*. The absolute file path where you want to save mixed audio file.     |    /path/to/output/file.wav 

**Impotant:** <br>
1. You can choose only `-asrc` or `-d` key. <br>
2. Number of effects and time stamps in time line should be the same. <br>

##### Example 1 - Mix 3 sound effects with source file
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

##### Example 2 - Mix 3 sound effects with silent file.
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

#### 1.2 Режим *ONLY VIDEO*

|Key       | Description                                                              | Value            |
|:-------- |--------------------------------------------------------------------------|------------------|
| `-fm`    | *Frame mask*.       |   /create_video/frame_%06d.png 
| `-CBR`   | *Constant bitrate*. |   2000k 
| `-ABR`   | *Average bitrate*.  |   200k 
| `-VBR`   | *Variable bitrate*. |   100k 300k 
| `-fps`   | *Framerate*.        |   24 
| `-out`   | *Output*. The absolute file path where you want to save mixed video file.| /path/to/output/file.mp4 

**Impotant:** <br>
1. You can choose only `-CBR` or `-ABR` or `-VBR`key. <br>

##### Example 1 - Create only video.

```
java -jar mixtool \
-fm /create_video/frame_%06d.png \
-ABR 1000k \
-fps 24 \
-out /SpilnaSprava/mixtool/output/ideaVideoAndAudio.mp4
```
*What command do:*<br>
1. Video will created from images with mask *frame_%06d.png* that located in folder *create_video*. <br>
2. Set average bitrate to *1000k*. <br>
3. Set framerate to 24 frames per second. <br>
4. Mixed file saved in */SpilnaSprava/mixtool/output/ideaVideoAndAudio.mp4*<br>

#### 1.3 Режим *VIDEO&AUDIO*
Для того щоб згенерувати відео, що містить в собі аудіо потрібно вказати всі параметри.<br>

##### Example 1 - Create video with audio.

```
java -jar mixtool \
-d 30 \
-se /sound_efects/car_x.wav \
/sound_efects/ahem_x.wav \
/sound_efects/applause_y.wav \
-t 0 5000L 10000 \
-fm /create_video/frame_%06d.png \
-ABR 1000k \
-fps 24 \
-out /SpilnaSprava/mixtool/output/ideaVideoAndAudio.mp4
```
*What command do:*<br>
1. Create silent audio file with duration of 30 seconds <br>
2. Uses three effects: *car_x.wav*, *ahem_x.wav*, *applause_y.wav*. <br>
3. Effects applies to 0, 5, 10 seconds. On the fifth second effect repeats. <br>
4. Video will created from images with mask *frame_%06d.png* that located in folder *create_video*. <br>
5. Set average bitrate to *1000k*. <br>
6. Set framerate to 24 frames per second. <br>
7. Mixed file saved in */SpilnaSprava/mixtool/output/ideaVideoAndAudio.mp4*<br>



