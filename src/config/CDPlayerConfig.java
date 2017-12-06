package config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import soundsystem.CDPlayer;
import soundsystem.CompactDisc;
import soundsystem.SgtPeppers;

@Configuration
//@ComponentScan(basePackages = {"soundsystem"})
public class CDPlayerConfig {

    //START Explicit declaration in the absence of @ComponentScan

    @Bean
    public CompactDisc sgtPeppers(){
        return new SgtPeppers();
    }

//    @Bean
//    public CDPlayer cdPlayer(){
//        return new CDPlayer(sgtPeppers());
//    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) //singleton = single for application, prototype= new every time, session and request do as you'd expect
//    @Scope(WebApplicationContext.SCOPE_SESSION) //need spring web stuff installed though
    public CDPlayer injectedCDPlayer(CompactDisc compactDisc){
        //use constructor instead - a bit more intuitive I think
        return new CDPlayer(compactDisc);
    }

    //END Explicit declaration in the absence of @ComponentScan

}
