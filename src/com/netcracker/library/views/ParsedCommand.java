package com.netcracker.library.views;

/**
 * Created by raumo0 on 21.10.16.
 */
class ParsedCommand {

    String command;

    String[] args;

    public ParsedCommand(String line) {
        String parts[] = line.split(" ");
        if (parts != null) {
            command = parts[0];
            if (parts.length > 1) {
                args = new String[parts.length - 1];
                System.arraycopy(parts, 1, args, 0, args.length);
            }
        }
    }
}
