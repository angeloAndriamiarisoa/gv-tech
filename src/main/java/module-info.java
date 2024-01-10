module com.techimage.projectjfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires kernel;
    requires layout;
    requires io;

    opens com.techimage.projectjfx to javafx.fxml;
    opens com.techimage.projectjfx.model;
    exports com.techimage.projectjfx;
    exports com.techimage.projectjfx.controller;
    exports com.techimage.projectjfx.controller.forms;

}