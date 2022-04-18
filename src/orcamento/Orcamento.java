
package orcamento;


public class Orcamento {
    private String cpfCliente;
    private String Servico; 
    private String valor;


    public String getValor() {
        return valor;
    }

   
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return the cpfCliente
     */
    public String getCpfCliente() {
        return cpfCliente;
    }

    /**
     * @param cpfCliente the cpfCliente to set
     */
    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    /**
     * @return the Servico
     */
    public String getServico() {
        return Servico;
    }

    /**
     * @param Servico the Servico to set
     */
    public void setServico(String Servico) {
        this.Servico = Servico;
    }

}