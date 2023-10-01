import java.util.*;

public class Environment {

	private Environment p;
	private TreeMap t;
	private int maxIdLength = 2;

	public Environment() {
		t = new TreeMap();
	}

	public Environment(Environment staticParent) {
		t = new TreeMap();
		p = staticParent;
	}
	public DenotableValue accessEnv(String id) {
		DenotableValue denotVal = (DenotableValue) t.get(id);
		if (denotVal == null)
			if (p == null) {
				ErrorMessage.print("id " + id + " undeclared");
			} else
				return p.accessEnv(id);
		else
			return denotVal;
	}
	public TreeMap t() {
		return t;
	}


	public DenotableValue accessFuncEnv(String id) {
		DenotableValue denotVal = (DenotableValue) p.t.get(id);
		if (denotVal == null || denotVal.cat() != 2) {
			ErrorMessage.print("fun " + id + " undeclared");
			return null;
		} else
			return denotVal;
	}

	public void updateEnvIntVar(String id, Integer intValue) {
		updateEnv(id, new DenotableValue(Category.VARINT, intValue));
	}

	public void updateEnvListVar(String id) {
		updateEnv(id, new DenotableValue(Category.VARLIST, new ArrayList()));
	}

	public void updateEnvFunc(String id) { // denotable val filled in later
		updateEnv(id, new DenotableValue(Category.IDFUNC, null));
	}

	public Environment newFunction() {
		return new Environment(this);
	}

	public Environment dupEnvironment() {
		return new Environment(this.p);
	}

	public void updateEnvFunc(String id, SyntaxTree syntaxTree, Environment env,
			LinkedHashMap<String, DenotableValue> params, int returnType) {
		updateEnv(id, new DenotableValue(Category.IDFUNC, new Procedure(env, params, syntaxTree, returnType)));
	}

	public void updateEnv(String id, DenotableValue denotableValue) {
		DenotableValue denotVal = (DenotableValue) t.get(id);
		if (denotVal != null && denotVal.val() != null)
			ErrorMessage.print("Id " + id + " previously dec");
		if (id.length() > maxIdLength)
			maxIdLength = id.length();
		t.put(id, denotableValue);
	}

	public void updateEnvVal(String id, DenotableValue denotableValue) {
		if (denotableValue.cat() != Category.IDFUNC) {
			DenotableValue denotVal = (DenotableValue) t.get(id);
			if (denotVal == null)
				if (p == null)
					ErrorMessage.print("id " + id + " undeclared");
				else
					p.updateEnvVal(id, denotableValue);
			else
				t.put(id, denotableValue);
		} else
			ErrorMessage.print("fun id " + id + " val not updatable");
	}

	public void print(String blockName) {
		System.out.println("");
		System.out.println("id Table for " + blockName);
		System.out.print("---------------------");
		for (int i = 0; i < blockName.length(); i++)
			System.out.print("-");
		System.out.println("");
		System.out.println("");
		System.out.print("Id");
		for (int i = 0; i <= maxIdLength - 2; i++)
			System.out.print(" ");
		System.out.println("Category");
		System.out.print("--");
		for (int i = 0; i <= maxIdLength - 2; i++)
			System.out.print(" ");
		System.out.println("--------");
		Iterator envIterator = t.entrySet().iterator();
		TreeMap procedureList = new TreeMap();
		while (envIterator.hasNext()) {
			Map.Entry envEntry = (Map.Entry) envIterator.next();
			String entryId = (String) envEntry.getKey();
			System.out.print(entryId);
			for (int i = 0; i <= maxIdLength - entryId.length(); i++)
				System.out.print(" ");
			DenotableValue entryDenotVal = (DenotableValue) envEntry.getValue();
			System.out.println(entryDenotVal);
			if (entryDenotVal.cat() == Category.IDFUNC)
				procedureList.put(entryId, entryDenotVal.val());
		}
		Iterator procIterator = procedureList.entrySet().iterator();
		while (procIterator.hasNext()) {
			Map.Entry procEntry = (Map.Entry) procIterator.next();
			String procId = (String) procEntry.getKey();
			((Procedure) procEntry.getValue()).print(procId);
		}
	}

}