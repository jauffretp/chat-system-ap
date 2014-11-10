package perso;

public class Etudiant extends Personne {

		public Etudiant(String nom, int age, float note) {
		super(nom, age);
		this.note = note;
	}

		float note;

		public float getNote() {
			return note;
		}

		public void setNote(float note) {
			this.note = note;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return super.toString() + ", note="+this.note;
		}
		
		
}
