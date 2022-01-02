package classfile;

import static classfile.data.SharedDateValue.setDefaultDate;

import classfile.ui.MainFrame;
import classfile.unitTest.*;

class Main {
    private void initializeData() {
        setDefaultDate();
    }

    private void executeProgram() {
        new MainFrame();
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
