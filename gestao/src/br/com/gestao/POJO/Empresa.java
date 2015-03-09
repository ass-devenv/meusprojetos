package br.com.gestao.POJO;


public class Empresa  {

	private int codigo;
	private String razao;
	private String fantazia;
	private String cpf_cnpj;
	private String ins_estadual;
	private String lougradouro;
	private String bairro;
	private String municipio;
	private String estado;
	private String cep;
	private String telefone;
	private String email;
	private byte foto;
	
	public Empresa(){
		
	}
	public Empresa(int codigo, String razao, String fantazia, String cpf_cnpj,
			String ins_estadual, String lougradouro, String bairro, String municipio, String estado, String cep,
			String telefone, String email, byte foto) {
		
		this.codigo = codigo;
		this.razao = razao;
		this.fantazia = fantazia;
		this.cpf_cnpj = cpf_cnpj;
		this.ins_estadual = ins_estadual;
		this.lougradouro = lougradouro;
		this.bairro = bairro;
		this.municipio = municipio;
		this.estado = estado;
		this.cep = cep;
		this.telefone = telefone;
		this.email = email;
		this.foto = foto;
		
		
	}
	
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getRazao() {
		return razao;
	}

	public void setRazao(String razao) {
		this.razao = razao;
	}

	public String getFantazia() {
		return fantazia;
	}

	public void setFantazia(String fantazia) {
		this.fantazia = fantazia;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public String getLougradouro() {
		return lougradouro;
	}

	public void setLougradouro(String lougradouro) {
		this.lougradouro = lougradouro;
	}
	
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getIns_estadual() {
		return ins_estadual;
	}
	
	public void setIns_estadual(String ins_estadual) {
		this.ins_estadual = ins_estadual;
	}
	
	public byte getFoto() {
		return foto;
	}
	
	public void setFoto(byte foto) {
		this.foto = foto;
	}	
	
	@Override
	public String toString() {
		return "Empresa [codigo=" + codigo + ", razao=" + razao + ", fantazia="
				+ fantazia + ", cpf_cnpj=" + cpf_cnpj + ", ins_estadual="
				+ ins_estadual + ", lougradouro=" + lougradouro + ", bairro="
				+ bairro + ", municipio=" + municipio + ", estado=" + estado
				+ ", cep=" + cep + ", telefone=" + telefone + ", email="
				+ email + ", foto=" + foto + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + codigo;
		result = prime * result
				+ ((cpf_cnpj == null) ? 0 : cpf_cnpj.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result
				+ ((fantazia == null) ? 0 : fantazia.hashCode());
		result = prime * result + foto;
		result = prime * result
				+ ((ins_estadual == null) ? 0 : ins_estadual.hashCode());
		result = prime * result
				+ ((lougradouro == null) ? 0 : lougradouro.hashCode());
		result = prime * result
				+ ((municipio == null) ? 0 : municipio.hashCode());
		result = prime * result + ((razao == null) ? 0 : razao.hashCode());
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (codigo != other.codigo)
			return false;
		if (cpf_cnpj == null) {
			if (other.cpf_cnpj != null)
				return false;
		} else if (!cpf_cnpj.equals(other.cpf_cnpj))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (fantazia == null) {
			if (other.fantazia != null)
				return false;
		} else if (!fantazia.equals(other.fantazia))
			return false;
		if (foto != other.foto)
			return false;
		if (ins_estadual == null) {
			if (other.ins_estadual != null)
				return false;
		} else if (!ins_estadual.equals(other.ins_estadual))
			return false;
		if (lougradouro == null) {
			if (other.lougradouro != null)
				return false;
		} else if (!lougradouro.equals(other.lougradouro))
			return false;
		if (municipio == null) {
			if (other.municipio != null)
				return false;
		} else if (!municipio.equals(other.municipio))
			return false;
		if (razao == null) {
			if (other.razao != null)
				return false;
		} else if (!razao.equals(other.razao))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}	
}

