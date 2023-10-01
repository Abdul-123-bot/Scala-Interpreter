//Name: Venugopal Gonela
//CSE machine name:cse01.cse.unt.edu
//Interpreter implementation
//Interpreter is a class to represent a denotational semantics equations for the SCALA
//programming language, described in the given assignments.

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class Interpreter {

	private List<Integer> result;
	
	public Interpreter() {
		this.result=new ArrayList<Integer>();
	}

	public List<Integer> getResult() {
		return result;
	}
	
	//Main program
	public void m(Environment env, SyntaxTree mainTree)
	{
		statement(env, mainTree);
	}

	private void statement(Environment env, SyntaxTree mainTree) {
		
		if (mainTree . root () . equals (":")) {
				statement (env, mainTree . left ());
				statement (env, mainTree . right ());
			 }
		else if (mainTree . root () . equals ("="))
			{
			 String id=mainTree . left() . left() . root();
			 DenotableValue dv = (DenotableValue) expression(env, mainTree . right());
			 int cat=env.accessEnv(id).cat();
			 if(cat == dv . cat())
			 {
				 env.updateEnvVal(id, dv);
			 }
			 else
					ErrorMessage . print ("type error");
		}
		else if (mainTree . root () . equals ("WHILE")) {
			DenotableValue dv = (DenotableValue) expression(env, mainTree . left());
			if(dv.cat() == Category . VARBOOLEAN)
			{
				if ((boolean) dv . val()) {
					statement (env, mainTree . right ());	// execute body of while loop
					statement (env, mainTree);		// execute while loop recursively
				   }
			}
			else
				ErrorMessage . print ("type error");
		   
		}
		
		//if E S1 else S2
		else if (mainTree . root () . equals ("if")) {
			DenotableValue dv = (DenotableValue) expression(env, mainTree . left());
			if(dv.cat() == Category . VARBOOLEAN)
			{
				if((Boolean)dv.val())
					statement (env, mainTree . right ());//S1 execution
				else
					if(mainTree.thirdTree()!=null)
					{
						   statement(env, mainTree . thirdTree()); //S2 execution
					}
			}
			else
				ErrorMessage . print ("type error");
		}
		
		else if (mainTree . root() .equals("println"))
		{
			DenotableValue dv = (DenotableValue) expression(env, mainTree . left());
			if (dv.cat() == Category . VARINT)
				this.result.add((Integer)dv.val());
			else
				ErrorMessage . print ("type error");
		}
		
		//check for return
		else if(mainTree . root() .equals("return"))
		{
			String id="return";
			DenotableValue dv = (DenotableValue) expression(env, mainTree . left());
			Object val=dv.val();//type checking to be done here
			int cat=env.accessEnv(id).cat();
			if(cat == dv . cat())
			{
				env.updateEnvVal(id, new DenotableValue( cat,val));
			}
			else
				ErrorMessage . print ("type error");
		}
				
		
	}

	private Object expression(Environment env, SyntaxTree eTree) {
		String root=eTree . root();
		
		//arithmetic operator check
		if(root . matches("(\\+|-|\\*|/)"))
		{
			DenotableValue leftDV= (DenotableValue) expression(env, eTree.left());
			SyntaxTree rightTree = eTree . right();
			DenotableValue rightDV=null;
			if(rightTree!=null)
			{
				rightDV = (DenotableValue) expression(env, rightTree);
				if(leftDV.cat() == Category.VARINT && rightDV.cat() == Category.VARINT)
				{
					if(root .equals("+"))
						return new DenotableValue(Category . VARINT,
								(Integer)leftDV.val()+(Integer)rightDV.val());
					else if(root .equals("-"))
						return new DenotableValue(Category . VARINT,
								(Integer)leftDV.val()-(Integer)rightDV.val());
					else if(root .equals("*"))
					{
						return new DenotableValue(Category . VARINT,
								(Integer)leftDV.val()*(Integer)rightDV.val());
					}
					else if(root .equals("/"))
					{
						Integer rightVal=(Integer)rightDV.val();
						if(rightVal!=0)
							return new DenotableValue(Category . VARINT,
									(Integer)leftDV.val()/rightVal);
						else
						{
							ErrorMessage . print ("Divide by zero error");
							return null;
						}
					}
					else
						return null;
				}
				else
				{
					ErrorMessage . print ("type error");
					return null;
				}
			}
			else
			{
				if(leftDV.cat() == Category.VARINT)
				{
					if(root .equals("+"))
						return new DenotableValue(Category . VARINT,
								(Integer)leftDV.val());
					else if(root .equals("-"))
						return new DenotableValue(Category . VARINT,
								0-(Integer)leftDV.val());
					else
						return null;
				}
				else
				{
					ErrorMessage . print ("type error");
					return null;
				}
			}
			
		}
		
		//boolean comparision operator check
		else if(root . equals("&&"))
		{
			DenotableValue leftDV= (DenotableValue) expression(env, eTree.left());
			if(leftDV.cat() == Category . VARBOOLEAN)
			{
				if((Boolean)leftDV.val())
				{
					DenotableValue rightDV = (DenotableValue) expression(env, eTree.right());
					if(rightDV.cat() == Category . VARBOOLEAN)
					{
						return rightDV;
					}
					else
					{
						ErrorMessage . print ("type error");
						return null;
					}
				}
				else
					return leftDV;
			}
			else
			{
				ErrorMessage . print ("type error");
				return null;
			}
		}
		else if(root . equals("||"))
		{
			DenotableValue leftDV= (DenotableValue) expression(env, eTree.left());
			//DenotableValue rightDV = (DenotableValue) expression(env, eTree.right());
			if(leftDV.cat() == Category . VARBOOLEAN)
			{
				if(!(Boolean)leftDV.val())
				{
					DenotableValue rightDV = (DenotableValue) expression(env, eTree.right());
					if(rightDV.cat() == Category . VARBOOLEAN)
					{
						return rightDV;
					}
					else
					{
						ErrorMessage . print ("type error");
						return null;
					}
				}
				else
					return leftDV;
			}
			else
			{
				ErrorMessage . print ("type error");
				return null;
			}
		}
		else if(root . equals("!"))
		{
			DenotableValue leftDV= (DenotableValue) expression(env, eTree.left());
			if(leftDV.cat() == Category . VARBOOLEAN)
			{
				if((Boolean)leftDV.val())
				{
					return new DenotableValue(Category . VARBOOLEAN, false);
				}
				else
					return new DenotableValue(Category . VARBOOLEAN, true);
			}
			else
			{
				ErrorMessage . print ("type error");
				return null;
			}
		}
		
		// == & != operations check
		else if(root . matches("(==|!=)"))
		{
			DenotableValue leftDV= (DenotableValue) expression(env, eTree.left());
			DenotableValue rightDV = (DenotableValue) expression(env, eTree.right());
			if((leftDV.cat() == Category.VARINT && rightDV.cat() == Category.VARINT) ||
					(leftDV.cat() == Category.VARLIST && rightDV.cat() == Category.VARLIST))
			{
				if(root .equals("=="))
					return new DenotableValue(Category . VARBOOLEAN,
							leftDV.val().equals(rightDV.val()));
				else if(root .equals("!="))
					return new DenotableValue(Category . VARBOOLEAN,
							!leftDV.val().equals(rightDV.val()));
				else
					return null;
			}
			else
			{
				ErrorMessage . print ("type error");
				return null;
			}
		}
		
		//Relational operators check
		else if(root . matches("(<|<=|>|>=)"))
		{
			DenotableValue leftDV= (DenotableValue) expression(env, eTree.left());
			DenotableValue rightDV = (DenotableValue) expression(env, eTree.right());
			if(leftDV.cat() == Category.VARINT && rightDV.cat() == Category.VARINT)
			{
				if(root .equals("<"))
					return new DenotableValue(Category . VARBOOLEAN,
							(Integer)leftDV.val()<(Integer)rightDV.val());
				else if(root .equals("<="))
					return new DenotableValue(Category . VARBOOLEAN,
							(Integer)leftDV.val()<=(Integer)rightDV.val());
				else if(root .equals(">"))
					return new DenotableValue(Category . VARBOOLEAN,
							(Integer)leftDV.val()>(Integer)rightDV.val());
				else if(root .equals(">="))
					return new DenotableValue(Category . VARBOOLEAN,
							(Integer)leftDV.val()>=(Integer)rightDV.val());
				else 
					return null;
			}
			else
			{
				ErrorMessage . print ("type error");
				return null;
			}
		}
		
		//check for list append operation
		else if (eTree . root() .equals("::"))
		{
			DenotableValue leftDV= (DenotableValue) expression(env, eTree.left());
			DenotableValue rightDV = (DenotableValue) expression(env, eTree.right());
			if(leftDV.cat() == Category.VARINT || rightDV.cat() == Category.VARLIST)
			{
				List<Integer> rightVal = (List<Integer>) rightDV . val();
				rightVal.add(0, (Integer)leftDV.val());
				return new DenotableValue(Category.VARLIST, rightVal);
			}
			else
			{
				ErrorMessage . print ("type error");
				return null;
			}
		}
		
		//check for head operation
		else if (eTree . root() .equals("head"))
		{
			DenotableValue dv = (DenotableValue) expression(env, eTree.left());
			if(dv.cat() == Category.VARLIST)
			{
				List lst=(List)dv.val();
				if(lst.size()>0)
					return new DenotableValue(Category . VARINT,
							lst.get(0));
				else
				{
					ErrorMessage . print ("error");
					return null;
				}
					
			}
			else
			{
				ErrorMessage . print ("type error");
				return null;
			}
		}
		
		//check for tail operation
		else if (eTree . root() .equals("tail"))
		{
			DenotableValue dv = (DenotableValue) expression(env, eTree.left());
			if(dv.cat() == Category.VARLIST)
			{
				List lst=(List)dv.val();
				if(!lst.isEmpty())
					if(lst.size()>1)
						return new DenotableValue(Category . VARLIST, 
								lst.subList(1, lst.size()));
					else
						return new DenotableValue(Category . VARLIST, 
								new ArrayList());
				else
				{
					ErrorMessage . print ("error");
					return null;
				}
			}
			else
			{
				ErrorMessage . print ("type error");
				return null;
			}
		}
		
		//check for isEmpty operation
		else if (eTree . root() .equals("isEmpty"))
		{
			DenotableValue dv=(DenotableValue)expression(env, eTree.left());
			if(dv.cat()==Category.VARLIST)
				return new DenotableValue(Category . VARBOOLEAN, ((List)dv.val()).isEmpty());
			else
			{
				ErrorMessage . print ("type error");
				return null;
			}
		}
		
		//many more operations to check here
		
		//check for identifier
		else if(eTree . root() .equals("id"))
		{
			String id=eTree . left() . root();
			DenotableValue dv = env.accessEnv(id);
			return new DenotableValue(dv.cat(), dv.val());
		}
		
		//check for int val
		else if(eTree . root() .equals("intValue"))
		{
			if (eTree . left() . root().equals("Nil"))
				return new DenotableValue(Category.VARLIST, new ArrayList());
			else
				return new DenotableValue(Category.VARINT, Integer.parseInt(eTree . left() . root()));
		}
		//check for functional call
		else if(eTree . root() .startsWith("apply "))
		{
			String funcID= eTree . root().split(" ")[1];
			DenotableValue funDV = env.accessFuncEnv(funcID);
			Procedure p=(Procedure) funDV.val();
			Iterator<Entry<String, DenotableValue>> it=p.env().map().entrySet().iterator();
			//Creating New environment
			//Environment newEnv= p.env().dupEnvironment();
			Environment newEnv= env.dupEnvironment();
			while(it.hasNext())
			{
				Entry<String, DenotableValue> formals=it.next();
				int cat = formals.getValue().cat();
				Object val=null;
				if(cat == 0)
					val=0;
				else
					val = new ArrayList();
				newEnv.updateEnv(formals.getKey(), new DenotableValue(cat, val));
			}
			
			List<Object> actuals = new ArrayList<Object>();
			for(SyntaxTree t : eTree . getTrees())
			{
				//actuals.add(expression(env, t));
				DenotableValue dv = (DenotableValue)expression(env, t);
				actuals.add(new DenotableValue(dv.cat(), dv.val()));
			}
			//Type and count checking of arguments
			
			//adding return as ID in new env
			newEnv.updateEnv("return",new DenotableValue(p.getReturnType(), null));
			
			it=p.getParameters().entrySet().iterator();
			Iterator<Object> it1 = actuals.iterator();
			if(p.getParameters().size() != actuals.size())
				ErrorMessage . print ("Number of parameters mismatch applying function "+funcID);
			while(it.hasNext() && it1.hasNext())
			{
				Entry<String, DenotableValue> param=it.next();
				DenotableValue argDV = (DenotableValue)it1.next();
				
				if(argDV . cat() == param . getValue() . cat())
				{
					//param.getValue().setValue(argDV.val());	
					int cat = argDV . cat();
					Object val = argDV . val();
					if(cat == 0)
						newEnv.updateEnv(param.getKey(),new DenotableValue(cat, val));
					else
							newEnv.updateEnv(param.getKey(),new DenotableValue(cat, new ArrayList((List)val)));
				}
				else
					ErrorMessage . print ("Parameter type mismatch applying function "+funcID);
			}
			
			statement(newEnv, p.syntaxTree());//Interpret function with new env
			DenotableValue dvNew= new DenotableValue(p.getReturnType(), newEnv.accessEnv("return").val());
			newEnv = null;
			return dvNew;
		}
		else
			return null;
	}
	
}
