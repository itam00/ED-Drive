package TDALista;

public class Alumno{
	protected String nombre;
	protected PositionList<Integer> cursadas;
	protected PositionList<Integer> aprobadas;

	public Alumno (String nom){
		nombre=nom;
		cursadas=new ListaDE<Integer>();
		aprobadas= new ListaDE<Integer>();
	}

	public String Nombre(){
		return nombre;
	}

	public void agregarCursada(int cod){
		if (!estaCursada(cod))
			cursadas.addLast(cod);
		else
			System.out.println ("ya está cursada");
	}

	private boolean estaCursada(int cod){
		boolean esta=false;
		if (!cursadas.isEmpty())
			try{
				Position<Integer> pos=cursadas.first();
				while (!esta && pos!=cursadas.last())
					if (pos.element() == cod)
						esta=true;
					else
						pos=cursadas.next(pos);
				esta= (esta || pos.element()==cod);
			}
			catch (EmptyListException | BoundaryViolationException | InvalidPositionException e){
				System.out.println (e.getMessage());
			}
		return esta;
	}

	public void agregarAprobada(int cod){
		if (estaCursada(cod))
			if (!estaAprobada(cod))
				aprobadas.addLast(cod);
			else
				System.out.println ("Ya esta aprobada");
		else
			System.out.println("No esta cursada");
	}

	private boolean estaAprobada(int cod){
		boolean esta=false;
			if (!aprobadas.isEmpty())
				try{
					Position<Integer> pos=aprobadas.first();
					while (!esta && pos!=aprobadas.last())
						if (pos.element()==cod)
							esta=true;
						else
							pos=aprobadas.next(pos);
					esta= (esta || pos.element()==cod);
				}
				catch (EmptyListException | BoundaryViolationException | InvalidPositionException e){
					System.out.println (e.getMessage());
				}
		return esta;
	}
	
	public static void main(String[] args){
		PositionList<Alumno> alumnos= new ListaDE<Alumno>();
		Alumno Alan= new Alumno("Alan");
		Alumno Melina= new Alumno("Melina");
		Alumno Laureano= new Alumno ("Laureano");

		Alan.agregarCursada(12);
		Alan.agregarAprobada(12);
		Melina.agregarCursada(15);
		Melina.agregarCursada(15);
		Laureano.agregarCursada(12);
		Laureano.agregarAprobada(15);
		alumnos.addLast(Alan);
		alumnos.addLast(Melina);
		alumnos.addLast(Laureano);
	}
}
