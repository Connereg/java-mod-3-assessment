public class RunAgain {
    private boolean runAgain;
    private UserInputInterface inputService;

    public RunAgain(UserInputInterface inputService) {
        this.runAgain = true;
        this.inputService = inputService;
    }

    public void runAgainPrompt() {
        String runAgainInput = inputService.receiveStringInput("Would you like to run this program again? (y/n) : ");
            if (runAgainInput.equals("y")) {
                runAgain = true;
            }
            else if (runAgainInput.equals("n")) {
                runAgain = false;
            }
            else {
                System.out.println("This is not a valid input, rerunning the program");
            }

    }

    public boolean isRunAgain() {
        return runAgain;
    }
}
