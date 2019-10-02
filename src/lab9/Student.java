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
	private int age;
	
	public Student(String name, int age) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.age = age;
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
		return String.format("Name: %s \n"
				+ "Age: %d\n\n", name, age);
	}

}
