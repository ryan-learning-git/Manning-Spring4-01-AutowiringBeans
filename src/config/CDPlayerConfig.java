package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import soundsystem.BlankDisc;
import soundsystem.CDPlayer;
import soundsystem.CompactDisc;
import soundsystem.SgtPeppers;

@Configuration
@PropertySource("classpath:/soundsystem/app.properties")
//@ComponentScan(basePackages = {"soundsystem"})
public class CDPlayerConfig {

    //START Explicit declaration in the absence of @ComponentScan

    @Autowired
    Environment env;

    @Bean
    @Primary
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
//    @Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.INTERFACES) //for injecting this into singletons - proxy will intercept requests and push them where they should go.
    public CDPlayer injectedCDPlayer(CompactDisc compactDisc){
        //use constructor instead - a bit more intuitive I think
        //If we inject a session/request-scope bean into a singleton bean stuff breaks - see Spring In Action 4 section 3.4 Scoping Beans
        return new CDPlayer(compactDisc);
    }

    @Bean
    public BlankDisc blankDisc(){
        return new BlankDisc(env.getProperty("disc.title"), env.getProperty("disc.artist"));
    }


    //END Explicit declaration in the absence of @ComponentScan

}
