package com.finalprojultimate.controller.command;

import com.finalprojultimate.controller.command.get.GetLogoutCommand;
import com.finalprojultimate.controller.command.get.GetMainCommand;
import com.finalprojultimate.controller.command.get.GetProductCatalogCommand;
import com.finalprojultimate.controller.command.login.LoginCommand;
import com.finalprojultimate.controller.command.login.LoginSubmitCommand;
import com.finalprojultimate.controller.command.registration.RegistrationCommand;
import com.finalprojultimate.controller.command.registration.RegistrationSubmitCommand;
import com.finalprojultimate.controller.command.registration.SuccessfulRegistrationCommand;

import java.util.HashMap;
import java.util.Map;

import static com.finalprojultimate.util.Path.*;

public class CommandHolder {
    private static final String GET = "GET:";
    private static final String POST = "POST:";

    private final UnsupportedPathCommand unsupportedPathCommand = new UnsupportedPathCommand();
    private final Map<String, Command> commands;

    public CommandHolder() {
        commands = new HashMap<>();
        initCommands();
    }

    private void initCommands() {
        commands.put(GET + MAIN, new GetMainCommand());
        commands.put(GET + LOGIN, new LoginCommand());
        commands.put(GET + LOGOUT, new GetLogoutCommand());
        commands.put(GET + REGISTRATION, new RegistrationCommand());
        commands.put(GET + SUCCESSFUL_REGISTRATION, new SuccessfulRegistrationCommand());
        commands.put(GET + PRODUCT_CATALOG, new GetProductCatalogCommand());
//        commands.put(GET + USER_INFO_USERNAME, new GetUserInfo());
//        commands.put(GET + SUBJECTS, new GetSubjectsCommand());
//        commands.put(GET + CONCRETE_SUBJECT, new GetTestsCommand());
//        commands.put(GET + CONCRETE_STUDENT_TEST, new GetStudentConcreteTestCommand());
//        commands.put(GET + TEST_RESULTS, new GetTestResultCommand());
//        commands.put(GET + CONCRETE_TUTOR_TEST, new GetTutorConcreteTestCommand());
//        commands.put(GET + STUDENTS_LIST, new GetStudentsCommand());
//        commands.put(GET + STUDENT_TESTS_LIST, new GetStudentTestsCommand());
        commands.put(POST + LOGIN, new LoginSubmitCommand());
        commands.put(POST + REGISTRATION, new RegistrationSubmitCommand());
//        commands.put(POST + ADD_SUBJECT, new PostAddSubjectCommand());
//        commands.put(POST + CONCRETE_SUBJECT, new PostAddTestCommand());
//        commands.put(POST + SAVE_TEST_RECORD, new PostSavePersonTestRecord());
//        commands.put(POST + CONCRETE_STUDENT_TEST, new PostAddAnswerCommand());
//        commands.put(POST + ADD_TUTOR_TASK, new PostAddTaskCommand());
//        commands.put(POST + UPDATE_TUTOR_TASK, new PostUpdateTaskCommand());
    }

    public Command getCommandByKey(String commandKey) {
        return commands.getOrDefault(commandKey, unsupportedPathCommand);
    }
}
