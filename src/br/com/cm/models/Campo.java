package br.com.cm.models;

import java.util.ArrayList;
import java.util.List;

import br.com.cm.exceptions.ExplosaoException;

public class Campo {

	private final int linha;
	private final int coluna;

	private boolean minado = false;
	private boolean aberto = false;
	private boolean marcado = false;

	private List<Campo> vizinhos = new ArrayList<>();

	public Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	private boolean add(Campo campo) {
		this.vizinhos.add(campo);
		return true;
	}

	public boolean adicionarVizinhos(Campo vizinho) {
		boolean linhaDiferente = this.linha != vizinho.linha;
		boolean colunaDiferente = this.coluna != vizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;

		int deltaLinha = Math.abs(this.linha - vizinho.linha);
		int deltaColuna = Math.abs(this.coluna - vizinho.coluna);
		int deltaGeral = deltaColuna + deltaLinha;

		if (deltaGeral == 1 && !diagonal) {
			return this.add(vizinho);
		} else if (deltaGeral == 2 && diagonal) {
			return this.add(vizinho);
		} else {
			return false;
		}
	}

	public void alternarMarcacao() {
		if (!this.aberto) {
			this.marcado = !this.marcado;
		}
	}

	public boolean abrir() {

		if (this.aberto && !this.marcado) {
			this.aberto = true;

			if (this.minado) {
				throw new ExplosaoException();
			}

			if (this.vizinhacaSegura()) {
				this.vizinhos.forEach(vizinho -> vizinho.abrir());
			}

			return true;

		} else {
			return false;
		}

	}

	public void minar() {
		this.minado = true;
	}

	public boolean isAberto() {
		return this.aberto;
	}

	public boolean vizinhacaSegura() {
		return this.vizinhos.stream().allMatch(vizinho -> !vizinho.minado);
	}

	public boolean isMarcado() {
		return this.marcado;
	}

	public boolean isFechado() {
		return !this.isAberto();
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

	public boolean isMinado() {
		return minado;
	}

	public void setMinado(boolean minado) {
		this.minado = minado;
	}

	public void setAberto(boolean aberto) {
		this.aberto = aberto;
	}

	public void setMarcado(boolean marcado) {
		this.marcado = marcado;
	}
}
