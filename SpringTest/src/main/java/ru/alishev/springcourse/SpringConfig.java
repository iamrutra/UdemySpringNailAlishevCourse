package ru.alishev.springcourse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.alishev.springcourse.genres.ClassicalMusic;
import ru.alishev.springcourse.genres.JazzMusic;
import ru.alishev.springcourse.genres.RockMusic;

import java.util.Arrays;
import java.util.List;

@Configuration
//@ComponentScan("ru.alishev.springcourse")
@PropertySource("musicPlayer.properties")
public class SpringConfig {
    @Bean
    public ClassicalMusic classicalMusic() {
        return new ClassicalMusic();
    }

    @Bean
    public RockMusic rockMusic() {
        return new RockMusic();
    }


    @Bean
    public Computer computer(){
        return new Computer();
    }

    @Bean
    public JazzMusic jazzMusic() {
        return new JazzMusic();
    }

    @Bean
    public List<Music> musicList() {
        // Этот лист неизменяемый (immutable)

        return Arrays.asList(classicalMusic(), rockMusic(), jazzMusic());
    }

    @Bean
    public MusicPlayer musicPlayer() {
        return new MusicPlayer(musicList());
    }


}
