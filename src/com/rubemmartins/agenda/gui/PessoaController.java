package com.rubemmartins.agenda.gui;

import com.rubemmartins.agenda.Main;
import com.rubemmartins.agenda.model.Pessoa;
import com.rubemmartins.agenda.util.DateUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PessoaController {
	
	@FXML
	private TableView<Pessoa> tabela;
	@FXML
	private TableColumn<Pessoa, String> colunaNome;
	@FXML
	private TableColumn<Pessoa, String> colunaSobreNome;
	
	@FXML
	private Label rotuloNome;
	@FXML
	private Label rotuloSobreNome;
	@FXML
	private Label rotuloDataNascimento;
	
	private Main main;
	
	public PessoaController() {}
	
	@FXML
	private void initialize() {
		colunaNome.setCellValueFactory(celula -> celula.getValue().nomeProperty());
		colunaSobreNome.setCellValueFactory(celula -> celula.getValue().sobrenomeProperty());
		
		mostraDetalhe(null);
		
		tabela.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> mostraDetalhe(newValue));
	}
	
	public void setMain(Main main) {
		this.main = main;
		this.tabela.setItems(main.getDados());
	}
	
	private void mostraDetalhe(Pessoa pessoa) {
		if(pessoa != null) {
			rotuloNome.setText(pessoa.getNome());
			rotuloSobreNome.setText(pessoa.getSobrenome());
			rotuloDataNascimento.setText(DateUtil.format(pessoa.getDataNascimento()));
		}else {
			rotuloNome.setText("");
			rotuloSobreNome.setText("");
			rotuloDataNascimento.setText("");
		}
	}
	
	@FXML
	private void handleNovaPessoa() {
		Pessoa temp = new Pessoa();
		boolean okClicked = main.mostrarContatoDialog(temp);
		if(okClicked) {
			main.getDados().add(temp);
		}
	}
	
	@FXML
	private void handleExcluiPessoa() {
		int selecionado = tabela.getSelectionModel().getSelectedIndex();
		if (selecionado >= 0) {
			tabela.getItems().remove(selecionado);
		} else {
			mostrarAlerta();
		}
	}
	
	@FXML
	private void handleEditaPessoa() {
		Pessoa selecionada = tabela.getSelectionModel().getSelectedItem();
		if(selecionada != null) {
			boolean okClicked = main.mostrarContatoDialog(selecionada);
			if(okClicked) this.mostraDetalhe(selecionada);
		}else {
			mostrarAlerta();
		}
	}

	private void mostrarAlerta() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Nenhuma seleção");
		alert.setHeaderText("Nenhuma Pessoa selecionada.");
		alert.setContentText("Por Favor, seleciona uma pessoa.");
		alert.showAndWait();
	}

}
