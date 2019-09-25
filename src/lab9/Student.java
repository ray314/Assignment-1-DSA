/**
 * 
 */
package lab9;

/**
 * @author fbb3628
 *
 */
public class Student {

	private String name;
	
	public Student(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	
	@Override
	public int hashCode()
	{
		char[] letters = name.toCharArray();
		int code = 0;
		
		for (char letter : letters)
		{
			code += Character.getNumericValue(letter);
		}
		return code;
	}
	
	public String toString()
	{
		return name;
	}

}
