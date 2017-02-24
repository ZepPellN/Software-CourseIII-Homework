import MySQLdb
import matplotlib.pyplot as plt

if __name__ == '__main__':
    conn = MySQLdb.connect(host='182.254.138.226', user='user', passwd='371071798@qq.com', db='GitMining', port=3306)
    cur = conn.cursor()
    cur.execute("SELECT DISTINCT full_name FROM Repo LIMIT 2")
    repos = cur.fetchall()
    plt.ylim(0, 20)
    plt.grid(True)
    color = ['r', 'b', 'w', 'g', 'y']
    i = -1
    for repo in repos:
        cur.execute("SELECT created_at, COUNT(*) FROM Commit WHERE repo_name='%s' GROUP BY created_at" % (repo[0]))
        data = cur.fetchall()
        print(data)
        c = [1, 2]
        date = [d[0] for d in data]
        count = [d[1] for d in data]
        i = i + 1
        plt.plot(date, count, color[i])
    plt.show()
    cur.close()
    conn.close()
