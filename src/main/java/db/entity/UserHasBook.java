package db.entity;

public class UserHasBook extends Entity{
    private int userId;
    private int bookId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "UserHasBook{" +
                "userId=" + userId +
                ", bookId=" + bookId +
                '}';
    }
}
