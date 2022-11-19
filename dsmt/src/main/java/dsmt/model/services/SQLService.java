package dsmt.model.services;

import org.springframework.stereotype.Service;

import dsmt.model.repositories.StatisticRepository;
import dsmt.model.utils.AbstractSQL;

@Service
public class SQLService extends AbstractSQL implements StatisticRepository {

	@Override
	public Object execute(S_ORDER proc, Object... params) throws Exception {
		return super.execute(proc.toString(), params);
	}

}
