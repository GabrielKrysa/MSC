package model;

public class Filme {
	private int filmeId;
	private String nome;
	private String dataLancamento;
	private String dataEncerramento;
        private String horario;
	
	public Filme(int filmeId, String nome, String dataLancamento, String dataEncerramento, String horario) {
		super();
		this.filmeId = filmeId;
		this.nome = nome;
		this.dataLancamento = dataLancamento;
		this.dataEncerramento = dataEncerramento;
                this.horario = horario;
	}

	public int getFilmeId() {
		return filmeId;
	}
	
	public void setFilmeId(int filmeId) {
		this.filmeId = filmeId;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDataLancamento() {
		return dataLancamento;
	}
	
	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	
	public String getDataEncerramento() {
		return dataEncerramento;
	}
	
	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}
}
