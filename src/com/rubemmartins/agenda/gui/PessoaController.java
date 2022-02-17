package com.rubemmartins.agenda.gui;

import com.rubemmartins.agenda.Main;
import com.rubemmartins.agenda.model.Pessoa;
import com.rubemmartins.agenda.util.DateUtil;

import javafx.fxml.FXML;
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

}
