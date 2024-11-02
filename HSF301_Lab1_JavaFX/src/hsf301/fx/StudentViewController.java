package hsf301.fx;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.hsf301.tri.pojo.Account;
import com.hsf301.tri.pojo.Student;
import com.hsf301.tri.service.IStudentService;
import com.hsf301.tri.service.StudentService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class StudentViewController implements Initializable {

	@FXML
	Label lbl_welcome;

	private Account acc;

	@FXML
	Button btnAddStudent;

	@FXML
	Button btnDeleteStudent;

	@FXML
	Button btnUpdateStudent;

	@FXML
	TableView<Student> tblStudents;

	@FXML
	TableColumn<Student, Integer> studentId;

	@FXML
	TableColumn<Student, String> firstName;

	@FXML
	TableColumn<Student, String> lastName;

	@FXML
	TableColumn<Student, Integer> mark;

	@FXML
	Button btnCancle;

	@FXML
	TextField txtFirstName;
	@FXML
	TextField txtLastName;
	@FXML
	TextField txtTotalMark;
	@FXML
	TextField txt_search;
	@FXML
	Button btn_search;
	@FXML
	private ComboBox<String> cmbSearchCriteria;

	private IStudentService studentService;

	private ObservableList<Student> studentModel;

	private int id;

	private String userName;

	public StudentViewController() {
		studentService = new StudentService();
	}

	public void initAccount(Account acc) {
		this.acc = acc;
		lbl_welcome.setText("Welcome " + acc.getEmail());
		setRole(acc);
	}

	@FXML
	public void btnAddStudent() {
		String firstName = txtFirstName.getText().trim();
		String lastName = txtLastName.getText().trim();
		String totalMarkStr = txtTotalMark.getText().trim();
		if (firstName.isEmpty() || lastName.isEmpty() || totalMarkStr.isEmpty()) {
			showAlert("Validation Error", "All fields must be filled out.");
			return;
		}
		try {
			int mark = Integer.parseInt(totalMarkStr);
			if (mark < 0 || mark > 10) {
				showAlert("Validation Error", "Total mark must be between 1 and 9.");
				return;
			}
			studentService = new StudentService();
			Student student = new Student(firstName, lastName, mark);
			studentService.save(student);
			refreshData();

		} catch (NumberFormatException e) {
			showAlert("Validation Error", "Total mark must be a valid number.");
		}
	}

	@FXML
	public void btnDeleteStudent() {
		studentService = new StudentService();
		Student student = studentService.findById(id);
		if (student == null) {
			System.out.println("Student not found!");
		} else {
			studentService.delete(id);
		}
		txtFirstName.setText(null);
		txtLastName.setText(null);
		txtTotalMark.setText(null);
		this.refreshData();
	}

	@FXML
	public void btnUpdateStudent() {
		Student student = studentService.findById(id);
		student.setFirstName(txtFirstName.getText());
		student.setLastName(txtLastName.getText());
		student.setMark(Integer.parseInt(txtTotalMark.getText()));
		studentService.update(student);
		refreshData();
	}

	@FXML
	public void tblStudentOnMouseClick() {
		int index = tblStudents.getSelectionModel().getSelectedIndex();
		id = studentId.getCellData(index);
		userName = firstName.getCellData(index);
		txtFirstName.setText(firstName.getCellData(index));
		txtLastName.setText(lastName.getCellData(index));
		txtTotalMark.setText(mark.getCellData(index).toString());
	}

	@FXML
	public void btnCancleOnAction() {
		System.exit(0);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// Initialize table columns
		studentId.setCellValueFactory(new PropertyValueFactory<>("Id"));
		firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
		mark.setCellValueFactory(new PropertyValueFactory<>("Mark"));

		cmbSearchCriteria.setItems(FXCollections.observableArrayList("FirstName", "LastName", "Mark"));
		cmbSearchCriteria.setValue("FirstName"); 

		this.refreshData();

	}

	private void refreshData() {
		// TODO Auto-generated method stub
		studentModel = FXCollections.observableArrayList(studentService.findAll());
		tblStudents.setItems(studentModel);
	}

	public void setRole(Account acc) {
		this.acc = acc;
		if (this.acc.getRoleID() == 0) {
			this.btnAddStudent.setDisable(true);
			this.btnDeleteStudent.setDisable(true);
			this.btnUpdateStudent.setDisable(true);
		}
	}

	private void showAlert(String title, String content) {
		javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}

	public void search(MouseEvent event) {
		String keySearch = txt_search.getText().trim();
		String searchCriteria = cmbSearchCriteria.getValue();

		if (keySearch.isEmpty()) {
			showAlert("Search Error", "Please enter a search term.");
			return;
		}

		List<Student> filteredStudents;

		switch (searchCriteria) {
		case "FirstName":
			filteredStudents = studentService.findAll().stream()
					.filter(s -> s.getFirstName().toLowerCase().contains(keySearch.toLowerCase())).toList();
			break;
		case "LastName":
			filteredStudents = studentService.findAll().stream()
					.filter(s -> s.getLastName().toLowerCase().contains(keySearch.toLowerCase())).toList();
			break;
		case "Mark":
			try {
				int mark = Integer.parseInt(keySearch);
				filteredStudents = studentService.findAll().stream().filter(s -> s.getMark() == mark).toList();
			} catch (NumberFormatException e) {
				showAlert("Validation Error", "Mark must be a valid number.");
				return;
			}
			break;
		default:
			showAlert("Search Error", "Invalid search criteria.");
			return;
		}

		load(filteredStudents);
	}

	private void load(List<Student> students) {
		// TODO Auto-generated method stub
		if (studentModel != null) {
			studentModel.clear();
		}
		studentModel = FXCollections.observableArrayList(students);
		tblStudents.setItems(studentModel);
	}
}
