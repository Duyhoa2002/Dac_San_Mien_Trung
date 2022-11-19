package dsmt.model.repositories;

public interface StatisticRepository {

	public Object execute(S_ORDER proc, Object...params) throws Exception;
	
	public enum S_ORDER {
		MIN_MAX("SELECT * FROM VIEW_SO_RANGE"),
		/**
		 * PROC_SO_TIME: CONTENT UPLOAD BY TIME
		 *	<h3 style="color: red; text-align: center;">PHẢI TRUYỀN ĐỦ 5 THAM SỐ BẤT KỂ LÀ NULL</h3>
		 *	@Top số lượng rows | mặc định 1000
		 *	@start thời gian bắt đầu | mặc định min regTime
		 *	@end thời gian kết thúc | mặc định max regTime
		 *	@at Chọn theo 1(YEAR) | 2(MONTH) | 3(DAY)
		 *	@desc sắp xếp theo số lượng
		 */
		QTY("{CALL PROC_SO_TIME(?, ?, ?, ?, ?)}");// ?[3](@at) = 1 || 2 || 3

		private String value;
		S_ORDER(String value) {
			this.value = value;
		}
		
		@Override
		public String toString() {
			return value;
		}
	}
}
