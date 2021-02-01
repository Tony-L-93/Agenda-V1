package dto;

public class DireccionDTO {

		private int idDireccion;
		private String calle;
		private int altura;
		private String piso;
		private String dpto;
		

		public DireccionDTO(String calle, int altura, String piso, String dpto) {
			super();
			this.calle = calle;
			this.altura = altura;
			this.piso = piso;
			this.dpto = dpto;
		}
		
		public DireccionDTO(int idDireccion,String calle, int altura, String piso, String dpto) {
			super();
			this.idDireccion = idDireccion;
			this.calle = calle;
			this.altura = altura;
			this.piso = piso;
			this.dpto = dpto;
		}
		
		public int getIdDireccion() {
			return idDireccion;
		}
		
		public void setIdDireccion(int idDireccion) {
			this.idDireccion = idDireccion;
		}
		
		public String getCalle() {
			return calle;
		}
		
		public void setCalle(String calle) {
			this.calle = calle;
		}
		
		public int getAltura() {
			return altura;
		}
		
		public void setAltura(int altura) {
			this.altura = altura;
		}
		
		public String getPiso() {
			return piso;
		}
		
		public void setPiso(String piso) {
			this.piso = piso;
		}
		
		public String getDpto() {
			return dpto;
		}
		
		public void setDpto(String dpto) {
			this.dpto = dpto;
		}
}
