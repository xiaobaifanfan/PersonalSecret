package referenceCase;

public class Case {
	void GetTable::get_first() {
	    bool change = true; //表示若改动了一处，则需要重新便利
	    bool is_empty; //表示产生式右端为空串
	    int t; 
	    //循环，直到没有改动为止，即change = false
	    while(change) {
	        change = false;
	        //循环每个文法
	        for(auto &g : G) {
	            is_empty = true;
	            t = 0;
	            while(is_empty && t < static_cast<int>(g.right.size())) {
	                is_empty = false;
	                if(!inVT(g.right[t])) {
	                    if(first[g.left].find(g.right[t]) == first[g.left].end()) {
	                        first[g.left].insert(g.right[t]);
	                        change = true;
	                    }
	                    continue;
	                }  
	                for(auto i : first[g.right[t]]) {
	                    if(first[g.left].find(i) == first[g.left].end()) {
	                        first[g.left].insert(i);
	                        change = true;
	                    }
	                }

	                if(first[g.right[t]].find("ε") != first[g.right[t]].end()) {
	                    is_empty = true;
	                    t++;
	                }
	            }
	            if(t == static_cast<int>(g.right.size()) && first[g.left].find("ε") == first[g.left].end()) {
	                first[g.left].insert("ε");
	                change = true;
	            }
	        }
	    }

	    first.erase("S");
	    
	}
}
