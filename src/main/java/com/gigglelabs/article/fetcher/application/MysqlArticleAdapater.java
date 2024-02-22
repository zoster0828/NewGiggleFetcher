package com.gigglelabs.article.fetcher.application;

import com.gigglelabs.article.fetcher.application.dto.ArticleDto;
import com.gigglelabs.article.fetcher.port.dto.ArticleNotFoundException;
import com.gigglelabs.article.fetcher.port.ArticlePersistencePort;

import java.sql.*;

public class MysqlArticleAdapater implements ArticlePersistencePort {
    private static final String URL = "jdbc:mysql://localhost:3306/giggle";
    private static final String USER = "gigglelabs";
    private static final String PASSWORD = "1234";

    @Override
    public int save(ArticleDto articleDto) {
        String sql = "INSERT INTO Articles (id, title, url, site, likes, views, sourceLikes, sourceViews, sourceDate, thumbnailUrl, preText) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, articleDto.id);
            pstmt.setString(2, articleDto.title);
            pstmt.setString(3, articleDto.url);
            pstmt.setString(4, articleDto.site);
            pstmt.setLong(5, articleDto.likes);
            pstmt.setLong(6, articleDto.views);
            pstmt.setLong(7, articleDto.sourceLikes);
            pstmt.setLong(8, articleDto.sourceViews);
            pstmt.setLong(9,  articleDto.sourceDate);
            pstmt.setString(10, articleDto.thumbnailUrl);
            pstmt.setString(11, articleDto.preText);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArticleDto get(String id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT * FROM Articles WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return ArticleDto.builder()
                                .id(resultSet.getString("id"))
                                .title(resultSet.getString("title"))
                                .url(resultSet.getString("url"))
                                .site(resultSet.getString("site"))
                                .likes(resultSet.getLong("likes"))
                                .views(resultSet.getLong("views"))
                                .sourceLikes(resultSet.getLong("sourceLikes"))
                                .sourceViews(resultSet.getLong("sourceViews"))
                                .sourceDate(resultSet.getLong("sourceDate"))
                                .thumbnailUrl(resultSet.getString("thumbnailUrl"))
                                .preText(resultSet.getString("preText"))
                                .build();
                    }

                    throw new ArticleNotFoundException(id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
