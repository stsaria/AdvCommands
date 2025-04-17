package si.f5.stsaria.advCommands;

import si.f5.stsaria.advCommands.variables.Variables;

import java.time.Instant;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static String variableSubstitution(Variables variables, String line){
        while(line.contains("<randuuid>")){
            line = line.replaceFirst("<randuuid>", UUID.randomUUID().toString().replace("-", ""));
        }
        line = line.replace("<unixtime>", String.valueOf(Instant.now().getEpochSecond()));
        boolean found;
        found = true;
        while (found) {
            found = false;
            Matcher matcher = Pattern.compile("<[a-zA-Z0-9.]+[+\\-*/%=><^][a-zA-Z0-9.]+>").matcher(line);
            while (matcher.find()) {
                String g = matcher.group();
                String prefixRemovedG = g.replaceFirst("<", "").replaceAll(">$", "");
                String firstVarValue = variables.get(prefixRemovedG.split("[+\\-*/%=><^]")[0]);
                String secondVarValue = variables.get(prefixRemovedG.split("[+\\-*/%=><^]")[1]);
                if (g.contains("=")) {
                    line = line.replace(g, firstVarValue.equals(secondVarValue) ? "true" : "false");
                } else {
                    try {
                        long firstVarValueInt = Long.parseLong(firstVarValue);
                        long secondVarValueInt = Long.parseLong(secondVarValue);
                        long ans = 0;
                        if (g.contains("+")) {
                            ans = firstVarValueInt + secondVarValueInt;
                        } else if (g.contains("-")) {
                            ans = firstVarValueInt - secondVarValueInt;
                        } else if (g.contains("*")) {
                            ans = firstVarValueInt * secondVarValueInt;
                        } else if (g.contains("/")) {
                            ans = firstVarValueInt / secondVarValueInt;
                        } else if (g.contains("%")) {
                            ans = firstVarValueInt % secondVarValueInt;
                        } else if (prefixRemovedG.contains("<")) {
                            line = line.replace(g, firstVarValueInt < secondVarValueInt ? "true" : "false");
                            matcher = Pattern.compile("<[a-zA-Z0-9.]+[+\\-*/%=><^][a-zA-Z0-9.]+>").matcher(line);
                            found = true;
                            continue;
                        } else if (prefixRemovedG.contains(">")) {
                            line = line.replace(g, firstVarValueInt > secondVarValueInt ? "true" : "false");
                            matcher = Pattern.compile("<[a-zA-Z0-9.]+[+\\-*/%=><^][a-zA-Z0-9.]+>").matcher(line);
                            found = true;
                            continue;
                        } else if (g.contains("^")) {
                            ans = (int) Math.pow(firstVarValueInt, secondVarValueInt);
                        }
                        line = line.replace(g, String.valueOf(ans));
                    } catch (Exception ignore) {
                        return "error: cant cast string to long";
                    }
                }
                matcher = Pattern.compile("<[a-zA-Z0-9.]+[+\\-*/%=><^][a-zA-Z0-9.]+>").matcher(line);
                found = true;
            }
            matcher = Pattern.compile("<[a-zA-Z0-9.]+\\?>").matcher(line);
            while (matcher.find()) {
                String g = matcher.group();
                line = line.replace(g, variables.contains(g.replaceFirst("<", "").replaceAll("\\?>$", "")) ? "true" : "false");
                matcher = Pattern.compile("<[a-zA-Z0-9.]+\\?>").matcher(line);
                found = true;
            }
            matcher = Pattern.compile("<[a-zA-Z0-9.]+>").matcher(line);
            while (matcher.find()) {
                String g = matcher.group();
                String variableValue = variables.get(g.replaceFirst("<", "").replaceAll(">$", ""));
                if (variableValue == null) continue;
                line = line.replace(g, variableValue);
                matcher = Pattern.compile("<[a-zA-Z0-9.]+>").matcher(line);
                found = true;
            }
        }
        return line;
    }
}
