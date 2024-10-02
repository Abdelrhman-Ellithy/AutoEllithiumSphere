package Ellithium.Internal;
import Ellithium.Utilities.Colors;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;

import static Ellithium.Utilities.logsUtils.*;

public class Reporter {
    public static void log(String message, LogLevel logLevel, String additionalParameter){
        switch(logLevel){
            case INFO_BLUE:
               info(Colors.BLUE+message+ Colors.RESET);
                Allure.step(message + additionalParameter, Status.PASSED);
                break;
            case INFO_GREEN:
                info(Colors.GREEN+message+ Colors.RESET);
                Allure.step(message + additionalParameter, Status.PASSED);
                break;
            case INFO_RED:
                info(Colors.RED+message+ Colors.RESET);
                Allure.step(message + additionalParameter, Status.FAILED);
                break;
            case INFO_YELLOW:
                info(Colors.YELLOW+message+ Colors.RESET);
                Allure.step(message + additionalParameter, Status.SKIPPED);
                break;
            case ERROR:
                error(Colors.RED+message+ Colors.RESET);
                Allure.step(message + additionalParameter, Status.FAILED);
                break;
            case TRACE:
                trace(Colors.BLUE+message+ Colors.RESET);
                Allure.step(message + additionalParameter, Status.PASSED);
                break;
            case WARN:
                warn(Colors.PINK+message+ Colors.RESET);
                Allure.step(message + additionalParameter, Status.PASSED);
                break;
            case DEBUG:
                debug(Colors.YELLOW+message+ Colors.RESET);
                Allure.step(message + additionalParameter, Status.PASSED);
            default: break;
        }
    }
    public static void log(String message, LogLevel logLevel){
            log(message,logLevel,"");
    }
}
