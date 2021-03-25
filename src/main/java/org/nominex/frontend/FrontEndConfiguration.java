package org.nominex.frontend;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(value = {"org/nominex/frontend/steps", "org/nominex/frontend/pages"})
@PropertySource(value = {"classpath:application.properties"})
public class FrontEndConfiguration {

}
