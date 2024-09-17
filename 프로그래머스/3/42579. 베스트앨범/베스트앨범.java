
import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public static class Music implements Comparable<Music> {
        int index;
        String genre;
        int play;

        public Music(int index, String genre, int play) {
            this.index = index;
            this.genre = genre;
            this.play = play;
        }

        @Override
        public int compareTo(Music o) {
            if (this.play == o.play) {
                return Integer.compare(this.index, o.index);
            }
            return Integer.compare(o.play, this.play);
        }
    }

    public static class MusicPriority implements Comparable<MusicPriority> {
        String genre;
        int play;

        public MusicPriority(String genre, int play) {
            this.genre = genre;
            this.play = play;
        }

        @Override
        public int compareTo(MusicPriority o) {
            return Integer.compare(o.play, this.play);
        }
    }

    public static int[] solution(String[] genres, int[] plays) {
        List<Integer> res = new ArrayList<>();

        List<Music> musicList = new ArrayList<>();
        for (int i = 0; i < plays.length; i++) {
            musicList.add(new Music(i, genres[i], plays[i]));
        }

        Map<String, Queue<Music>> genreSortGroup = musicList.stream()
                .collect(Collectors.groupingBy(music -> music.genre, Collectors.toCollection(PriorityQueue::new)));

        Map<String, Integer> countGroup = musicList.stream()
                .collect(Collectors.groupingBy(music -> music.genre, Collectors.summingInt(music -> music.play)));

        Queue<MusicPriority> countQueue = new PriorityQueue<>();
        for (Map.Entry<String, Integer> e : countGroup.entrySet()) {
            countQueue.add(new MusicPriority(e.getKey(), e.getValue()));
        }

        while (!countQueue.isEmpty()) {
            MusicPriority musicPriority = countQueue.poll();

            int pollCount = 0;
            Queue<Music> music = genreSortGroup.get(musicPriority.genre);
            while (!music.isEmpty()) {
                res.add(music.poll().index);
                if (++pollCount == 2) {
                    break;
                }
            }
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
