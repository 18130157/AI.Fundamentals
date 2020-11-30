package task2.model;

public enum MyLocationState {
	CLEAN, DIRTY, WALL, LOCK
	// LOCK dùng để đánh dấu các DIRTY mà Agent không có đường đi đến đó
	// => Agent bỏ qua các LOCK để biết khi nào dừng tìm kiếm
	// Hiện tại còn dùng Random nên chưa dùng đến LOCK
}
