'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            users: [],
            countries : [],
            selectValue: ""
        };
    }

    componentDidMount() {
        client({method: 'GET', path: '/getUsers'}).done(response => {
            this.setState({users: response.entity});
        });
    }

    render() {
        return (
            <CountriesList users={this.state.users}/>
        )
    }
}

class CountriesList extends React.Component {

    constructor(props){
        super(props)
        this.state = {users: this.props.users, countries : this.mapCountries(this.props.users), selectValue: "" }
    }

    mapCountries = (users) => {
        const uniqueCountries = [...new Set(users.map(user => user.location))];
        return (uniqueCountries
            .map(country =>
            <option value={country}>{country}</option>
        ))
    }

    handleChange = (e) => {
        this.setState({ selectValue: e.target.value });
    }

    loadUsers = () => {
        client({method: 'GET', path: '/getUsers'}).done(response => {
            this.setState({users: response.entity, countries: this.mapCountries(response.entity)});
        });
    }

    handleCountrySelect() {
        return (
            <select
                className="form-control"
                aria-label="Floating label select example"
                onChange={this.handleChange}>
                <option value="choose" disabled selected="selected">
                    -- Select country --
                </option>
                {this.state.countries}
            </select>
        )
    }

    render() {
        return (
            <div>
                {this.handleCountrySelect()}
                <button onClick={this.loadUsers}>
                    load more!
                </button>
                <UserList users={this.state.users} selectValue={this.state.selectValue}/>
            </div>
        )
    }
}

class UserList extends React.Component{
    render() {
        const users = this.props.users
            .filter(user => this.props.selectValue === user.location)
            .map(user =>
            <User key={user} user={user}/>
        );
        return (
            <table>
                <tbody>
                <tr>
                    <th>Name</th>
                    <th>Gender</th>
                    <th>Email</th>
                </tr>
                {users}
                </tbody>
            </table>
        )
    }
}

class User extends React.Component{
    render() {
        return (
            <tr>
                <td>{this.props.user.name}</td>
                <td>{this.props.user.gender}</td>
                <td>{this.props.user.email}</td>
            </tr>
        )
    }
}

ReactDOM.render(
    <App />, document.getElementById('app')
)