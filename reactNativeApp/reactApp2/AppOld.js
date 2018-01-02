import React, {Component} from 'react';
import {AppRegistry, Image, ListView, StyleSheet, Text, View} from 'react-native';


const styles = StyleSheet.create({
  container: {flex: 1, paddingTop: 22},
  row: {
    padding: 15,
    marginBottom: 5,
    backgroundColor: 'skyblue',
  },
});

const rows =
    [
      {name: 'dance', description: 'move feet', hours: 3},
      {name: 'sing', description: 'move mouth', hours: 3},
      {name: 'eat', description: 'sh & di', hours: 3}
    ]

    export class HobbyList extends Component {
  constructor() {
    super();
    const ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
    this.state = {
      dataSource: ds.cloneWithRows(rows),
      dataArray: rows,
    };
  }
  render() {
    return (
      <ListView
    style = {styles.container} dataSource = {
        this.state.dataSource} renderRow = {(rowData) =><Text>{rowData}</Text>
  }
  />
    );
    }
    }

  export class Hobby extends Component {
    constructor(props){super(props)} render() {
      let name = this.props.name;
      let description = this.props.description;
      let hours = this.props.hours;
      return (<View style = {styles}>
              <Text>{name} {description} {hours}</Text >
      </View>);
    }
    }
  export default class HelloWorldApp extends Component {
    render() {
    return (
      <View style={styles.container}>
        <Text>Hobbies List</Text>
      <HobbyList />< /View>
    );
  }
}

