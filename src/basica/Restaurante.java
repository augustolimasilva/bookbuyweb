package basica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idRestaurante;

	private Endereco endereco;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false, unique = true)
	private String cnpj;

	@Column(nullable = false)
	private String telefone;

	@Enumerated(EnumType.STRING)
	private Situacao situacao;

	@Column(nullable = true)
	private byte[] logo;

	@Column(nullable = false)
	private String email;

	@OneToMany(mappedBy = "restaurante")
	private List<Mesa> mesas;

	@ManyToOne
	@JoinColumn(name = "idUsuario", foreignKey = @ForeignKey(name = "fk_restaurante_usuario") )
	private Usuario usuario;

	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos;

	@ManyToMany
	@JoinTable(name = "restaurante_Tipo", joinColumns = @JoinColumn(name = "idRestaurante") , inverseJoinColumns = @JoinColumn(name = "idTipo") )
	private List<TipoRestaurante> tipos;

	@OneToMany(mappedBy = "restaurante")
	private List<Reserva> reservas;

	@OneToMany(mappedBy = "restaurante")
	private List<Promocao> promocoes;

	@Column(nullable = false)
	@OneToMany(mappedBy = "restaurante")
	private List<Rate> rate;

	public Restaurante() {
		this.endereco = new Endereco();
		this.usuario = new Usuario();
		this.produtos = new ArrayList<Produto>();
		this.mesas = new ArrayList<Mesa>();
		this.tipos = new ArrayList<TipoRestaurante>();
		this.reservas = new ArrayList<Reserva>();
		this.promocoes = new ArrayList<Promocao>();
		this.rate = new ArrayList<Rate>();
	}

	public List<Rate> getRate() {
		return rate;
	}

	public void setRate(List<Rate> rate) {
		this.rate = rate;
	}

	public List<TipoRestaurante> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoRestaurante> tipos) {
		this.tipos = tipos;
	}

	public int getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(int idRestaurante) {
		this.idRestaurante = idRestaurante;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public List<Promocao> getPromocoes() {
		return promocoes;
	}

	public void setPromocoes(List<Promocao> promocoes) {
		this.promocoes = promocoes;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Mesa> getMesas() {
		return mesas;
	}

	public void setMesas(List<Mesa> mesas) {
		this.mesas = mesas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + idRestaurante;
		result = prime * result + Arrays.hashCode(logo);
		result = prime * result + ((mesas == null) ? 0 : mesas.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((produtos == null) ? 0 : produtos.hashCode());
		result = prime * result + ((promocoes == null) ? 0 : promocoes.hashCode());
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
		result = prime * result + ((reservas == null) ? 0 : reservas.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result + ((tipos == null) ? 0 : tipos.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Restaurante other = (Restaurante) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (idRestaurante != other.idRestaurante)
			return false;

		return true;
	}
	
	
	
}