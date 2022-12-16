var path = require('path');

module.exports = {
    entry: './app/index.js',
    output: {
        path: path.resolve('../resources/dist'),
        filename: 'index_bundle.js',
    },
    module: {
        rules: [
            { test: /\.(js)$/, use: 'babel-loader' },
            { test: /\.(css)$/, use: ['style-loader', 'css-loader' ]},
            {
                test: /\.s[ac]ss$/i,
                use: [
                    'style-loader',
                    'css-loader',
                    'sass-loader',
                ],
            },
            {
                test: /\.svg$/,
                use: [
                    {
                        loader: 'svg-url-loader',
                        options: {
                            limit: 10000,
                        },
                    },
                ],
            }
        ],
    },
    mode: 'production',
    devServer: {
        contentBase: path.join(__dirname, '../resources/dist'),
        compress: true,
        port: 8080,
        writeToDisk: true
    }
};