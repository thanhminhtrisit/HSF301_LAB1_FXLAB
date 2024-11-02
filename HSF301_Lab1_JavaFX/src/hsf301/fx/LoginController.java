package hsf301.fx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;

import com.hsf301.tri.pojo.Account;
import com.hsf301.tri.repo.AccountRepo;
import com.hsf301.tri.repo.IAccountRepo;

import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class LoginController {

	@FXML
	Button btnLogin;
	@FXML
	Button btnCancel;
	@FXML
	TextField txtUserName;
	@FXML
	PasswordField txtPassword;

	private IAccountRepo accountRepo;

	public LoginController() {
		accountRepo = new AccountRepo();
	}

	private void changeToView(Account acc, MouseEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentView.fxml"));
			Parent root = loader.load();
			StudentViewController controller = loader.getController();
			controller.initAccount(acc);

			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@FXML
	public void closeLoginForm(ActionEvent event) {
		System.out.println("Close Form");
		System.exit(0);
	}

	private void displayAlert() {
		Alert a = new Alert(AlertType.ERROR);
		a.setContentText("Wrong pass or username! Try again!");
		a.showAndWait();
	}

	@FXML
	public void btnloginOnMouseClicked(MouseEvent event) {
		String userName = txtUserName.getText();
		String pass = txtPassword.getText();
		Account acc = accountRepo.login(userName, pass);
		if (acc == null) {
			displayAlert();
			return;
		}
		changeToView(acc, event);
	}
}
