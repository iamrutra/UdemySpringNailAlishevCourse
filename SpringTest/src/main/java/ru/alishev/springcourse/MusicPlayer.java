package ru.alishev.springcourse;

import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Neil Alishev
 */
//@Component
public class MusicPlayer {

    @Value("${musicPlayer.name}")
    private String name;

    @Value("${musicPlayer.volume}")
    private int volume;

    public MusicPlayer() {

    }

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }

    private List<Music> musicList;

//    @Autowired
    public MusicPlayer(List<Music> musicList) {
        this.musicList = musicList;
    }

//    @Autowired
//    public MusicPlayer(ClassicalMusic classicalMusic, RockMusic rockMusic) {
//        this.classicalMusic = classicalMusic;
//        this.rockMusic = rockMusic;
//    }
//
//
    public String playMusic() {
//        Random random = new Random();
//        int randomNum = random.nextInt(3);
//
//        if (genre == MusicGenre.CLASSICAL){
//            System.out.println(classicalMusic.getSong()[randomNum]);
//        }
//        else{
//            System.out.println(rockMusic.getSong()[randomNum]);
//        }
        Random random = new Random();

        return "Playing: " + musicList.get(random.nextInt(musicList.size())).getSong();
    }
}
