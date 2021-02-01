package dto;

public class LocalidadDTO {
		private int id;
		private String localidad;
		private String provincia;
		private String pais;
		
		public LocalidadDTO(int id, String localidad, String provincia, String pais) {
			super();
			this.id = id;
			this.localidad = localidad;
			this.provincia = provincia;
			this.pais = pais;
		}
		public LocalidadDTO(int id) {
			this.id = id;
			// TODO Auto-generated constructor stub
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getLocalidad() {
			return localidad;
		}
		public void setLocalidad(String localidad) {
			this.localidad = localidad;
		}
		public String getProvincia() {
			return provincia;
		}
		public void setProvincia(String provincia) {
			this.provincia = provincia;
		}
		public String getPais() {
			return pais;
		}
		public void setPais(String pais) {
			this.pais = pais;
		}
		
		
}
