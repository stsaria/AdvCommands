package si.f5.stsaria.advCommands;

import si.f5.stsaria.advCommands.variables.Variables;

import java.time.Instant;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final Pattern OPERATION_PATTERN = Pattern.compile("<([a-zA-Z0-9.]+)([+\\-*/%=><^])([a-zA-Z0-9.]+)>");
    private static final Pattern CONTAINS_PATTERN = Pattern.compile("<([a-zA-Z0-9.]+)\\?>");
    private static final Pattern CONTAINS_DIRECT_PATTERN = Pattern.compile("<([a-zA-Z0-9.]+)\\?\\?>");
    private static final Pattern VARIABLE_PATTERN = Pattern.compile("<([a-zA-Z0-9.]+)>");
    private static final Pattern ESCAPED_PATTERN = Pattern.compile("<([a-zA-Z0-9.]+)!>");

    public static String variableSubstitution(Variables variables, String line) {
        line = line.replace("<unixtime>", String.valueOf(Instant.now().getEpochSecond())).replace("<nl>", "\n");

        while (line.contains("<randuuid>")) {
            line = line.replaceFirst("<randuuid>", UUID.randomUUID().toString().replace("-", ""));
        }

        String beforeLine;
        do {
            beforeLine = line;
            line = operations(variables, line);
            line = contains(variables, line);
            line = containsDirect(variables, line);
            line = variables(variables, line);
            line = escapes(line);
        } while (!line.equals(beforeLine));
        return line;
    }

    private static String operations(Variables variables, String line) {
        Matcher matcher = OPERATION_PATTERN.matcher(line);
        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            String left = variables.get(matcher.group(1));
            String op = matcher.group(2);
            String right = variables.get(matcher.group(3));

            if (left == null || right == null) continue;

            String replacement = calcOperation(left, op, right).replaceFirst(".0$", "");
            matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static String calcOperation(String left, String op, String right) {
        try {
            if (op.equals("=")) {
                return left.equals(right) ? "true" : "false";
            }
            double a = Double.parseDouble(left);
            double b = Double.parseDouble(right);
            return switch (op) {
                case "+" -> String.valueOf(a + b);
                case "-" -> String.valueOf(a - b);
                case "*" -> String.valueOf(a * b);
                case "/" -> b == 0 ? "0yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy" : String.valueOf(a / b);
                case "%" -> String.valueOf(a % b);
                case "<" -> a < b ? "true" : "false";
                case ">" -> a > b ? "true" : "false";
                case "^" -> String.valueOf(Math.pow(a, b));
                default -> "NaN";
            };
        } catch (NumberFormatException e) {
            return "NaN";
        }
    }

    private static String contains(Variables variables, String line) {
        Matcher matcher = CONTAINS_PATTERN.matcher(line);
        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            String var = matcher.group(1);
            matcher.appendReplacement(sb, variables.contains(var) ? "true" : "false");
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static String containsDirect(Variables variables, String line) {
        Matcher matcher = CONTAINS_DIRECT_PATTERN.matcher(line);
        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            String var = matcher.group(1);
            matcher.appendReplacement(sb, variables.contains(var) ? "true" : "false");
            matcher = CONTAINS_DIRECT_PATTERN.matcher(line);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static String variables(Variables variables, String line) {
        Matcher matcher = VARIABLE_PATTERN.matcher(line);
        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            String var = matcher.group(1);
            String value = variables.get(var);
            matcher.appendReplacement(sb, value != null ? Matcher.quoteReplacement(value) : matcher.group());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static String escapes(String line) {
        return ESCAPED_PATTERN.matcher(line).replaceAll(mr -> "<" + mr.group(1) + ">");
    }
}
