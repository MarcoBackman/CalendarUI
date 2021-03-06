package classfile;

import classfile.ui.MainFrame;
import classfile.data.SharedDateValue;
import classfile.unitTest.*;

class Main {
    private void initializeData() {
        SharedDateValue.setDefaultDate();
    }

    private void executeProgram() {
        MainFrame mainFrameInstance = new MainFrame();
    }


    private void Testing() {
        TestDateAssert test = new TestDateAssert(2021, 6, 5);
        test.printSpecifiedDateInfo(2021, 6, 5);
        test.testTotalDate(31);
    }

    public static void main (String[] args) {
        Main mainInstance = new Main();
        mainInstance.initializeData();
        mainInstance.executeProgram();
        mainInstance.Testing();
    }
}
