package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.Conexion;

public class DaoAlumno {
	public arrayList<Alumno> getAlumnos() {
		ResultSet rs;
		ArrayList<Alumno> lista = new ArrayList<Alumno>();

		Connection con = Conexion.conecta();
		Statement st;
		try {
			st = con.createStatement();
			String ordenSql = "select dni, nombre, curso from alumno";
			rs = st.executeQuery(ordenSql);

			while (rs.next()) {
				Alumno alumno = new Alumno();
				alumno.setDNI(rs.getString("dni"));
				alumno.setNombre(rs.getString("nombre"));
				alumno.setGrupo(rs.getString("curso"));
				lista.add(alumno);
			}
			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	public Alumno getAlumno(String dni) {
		ResultSet rs;
		Connection con = Conexion.conecta();
		Statement st;
		Alumno alumno = new Alumno();
		try {
			st = con.createStatement();
			String ordenSql = "select dni, nombre, curso from alumno where dni = " + dni;
			rs = st.executeQuery(ordenSql);

			alumno.setDNI(rs.getString("dni"));
			alumno.setNombre(rs.getString("nombre"));
			alumno.setGrupo(rs.getString("curso"));
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alumno;
	}
	
	
	public void insertaAlumno(Alumno alumno) {
		Connection con = Conexion.conecta();
        try {
            String ordenSQL;
            ordenSQL = "insert into alumno values(?,?,?)";
            PreparedStatement st = con.prepareStatement(ordenSQL);
            st.setString(1, alumno.getDNI());
            st.setString(2, alumno.getNombre());
            st.setString(3, alumno.getGrupo());
            st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar datos en la BDs: " + e.getMessage());
        }
    }
	
	public int borraAlumno(String dni) {
        int borrados=-1;
		Connection con = Conexion.conecta();
        String ordenSQL = "delete from Alumno where clave=?";
        try {
            PreparedStatement st = con.prepareStatement(ordenSQL);
            st.setString(1, dni);
            borrados = st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar datos en la BDs: " + e.getMessage());
        }
        return borrados;
    }

	
	


}
