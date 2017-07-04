import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Here we concern ourselves with all of the issues of actually parsing input lines
 * The rest of the program is structured so that we can assume that only valid AddInstructions are acceptable
 *
 * The parser itself is very strict with the instructions. They must be precisely as given in the instructions,
 * no changes of case are allowed, no additional spaces are allowed (including before or after the instruction on the
 * same line.
 */

public class Parser {

    private static final String REGEX_PATTERN = "^\\b(add|remove|is linked)\\b (\\d+) (\\d+)$";
    private static final Pattern P = Pattern.compile(REGEX_PATTERN);

    public static void main(String[] args) throws IOException {
        GraphMaintainer gm = new GraphMaintainer();
        // TODO: Please replace the following line with the pointer to the file you want to pull from
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            processLine(gm, line);
        }
    }

    /**
     * Validate and process a new line. This includes throwing it away if it doesn't do anything.
     * The key part of this function is that it should be really robust. Nothing should faze it
     */
    public static void processLine(GraphMaintainer gm, String line) {
        if (gm == null || line == null) {
            return;
        }
        ValidInstruction validatedInstruction = validateLine(line);
        if (validatedInstruction != null) {
            validatedInstruction.processValidLine(gm);
        }
    }

    /**
     * @return null if the line does not validate, otherwise, an instruction object that 'processValidLine'
     * can be called on with a graphMaintainer parameter
     */
    private static ValidInstruction validateLine(String line) {
        if (!line.matches(REGEX_PATTERN)) {
            return null;
        }
        Matcher m = P.matcher(line);
        m.find();

        // Group 0 is the whole line, group 1 is the 'add', 'remove' and 'is Linked', group 2 and 3 are the Longs
        Long firstNum = Long.valueOf(m.group(2));
        Long seconNum = Long.valueOf(m.group(3));

        return initializeValidInstruction(m.group(1), firstNum, seconNum);
    }

    private static ValidInstruction initializeValidInstruction(String instructionType, Long firstNum, Long seconNum) {
        if ("add".equals(instructionType)) {
            return new ValidAddInstruction(firstNum, seconNum);
        }
        if ("remove".equals(instructionType)) {
            return new ValidRemoveInstruction(firstNum, seconNum);
        }
        if ("is linked".equals(instructionType)) {
            return new ValidIsLinkedInstruction(firstNum, seconNum);
        }
        return null;
    }


    private static abstract class ValidInstruction {
        protected AddInstruction ai;

        public ValidInstruction(Long firstNumber, Long secondNumber) {
            ai = new AddInstruction(firstNumber, secondNumber);
        }

        abstract void processValidLine(GraphMaintainer graphMaintainer);
    }

    private static class ValidAddInstruction extends ValidInstruction {

        public ValidAddInstruction(Long firstNumber, Long secondNumber) {
            super(firstNumber, secondNumber);
        }

        @Override
        public void processValidLine(GraphMaintainer graphMaintainer) {
            graphMaintainer.add(ai);
        }
    }

    private static class ValidRemoveInstruction extends ValidInstruction {

        public ValidRemoveInstruction(Long firstNumber, Long secondNumber) {
            super(firstNumber, secondNumber);
        }

        @Override
        public void processValidLine(GraphMaintainer graphMaintainer) {
            graphMaintainer.remove(ai);
        }
    }

    // In this case, to reduce the amount of duplicate code, we have added the AddInstruction class as a
    // convenience mechanism for storing the two numbers that we need to pass to the linked instruction.
    private static class ValidIsLinkedInstruction extends ValidInstruction {

        public ValidIsLinkedInstruction(Long firstNumber, Long secondNumber) {
            super(firstNumber, secondNumber);
        }

        @Override
        public void processValidLine(GraphMaintainer graphMaintainer) {
            System.out.println(graphMaintainer.isLinked(ai.getLeft(), ai.getRight()));
        }
    }

}
