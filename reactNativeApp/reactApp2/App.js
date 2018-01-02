import React, {Component} from 'react';
import {Alert, Button, Linking, ListView, StyleSheet, Text, TextInput, TouchableHighlight, View} from 'react-native';
import {StackNavigator} from 'react-navigation';


// Row data (hard-coded)
rows =
    [
      {id: 0, text: 'Dancing', hours: 5}, {id: 1, text: 'Painting', hours: 1},
      {id: 2, text: 'Singing', hours: 10}
    ]


const rowHasChanged = (r1, r2) => r1 !== r2

// this creates a new DataSource with the checking function from above
const ds = new ListView.DataSource({rowHasChanged})


class HobbyList extends React.Component {
  constructor(props) {
    super(props);
    // var ds = new ListView.DataSource({rowHasChanged});
    this.state = {
      dataSource: ds.cloneWithRows(rows),
    }
    // this.state.dataSource.cloneWithRows(rows);
  }

  static navigationOptions = {
    title: 'Browse Screen',
  }

  _onPressRow(rowData) {
    const {navigate} = this.props.navigation;
    navigate('Edit', {'row': rowData})

        var newDs = [];
    newDs = rows.slice();
    this.setState({dataSource: ds.cloneWithRows(newDs)});

  }

    _onLongPressRow(rowData) {
        const {navigate} = this.props.navigation;
        navigate('Delete', {'row': rowData})

        var newDs = [];
        newDs = rows.slice();
        this.setState({dataSource: ds.cloneWithRows(newDs)});

    }

  componentDidMount() {
    var newDs = [];
    newDs = rows.slice();
    this.setState({dataSource: ds.cloneWithRows(newDs)});
  }

  renderRow = (rowData) => {
    return (<TouchableHighlight onPress = {() => this._onPressRow(rowData)} onLongPress = {() => this._onLongPressRow(rowData)}>
            <Text style = {styles.row}>Title:{rowData.text} Hours Invested:
                {rowData.hours}</Text>
			</TouchableHighlight>)
  }
  addHobby(){
      const {navigate} = this.props.navigation;
      navigate('Add')
      var newDs = [];
      newDs = rows.slice();
      this.setState({dataSource: ds.cloneWithRows(newDs)});
      console.log(rows)
  }

  render() {
      return (
          <View style={styles.container}>
              <Button onPress={() => this.addHobby()} title=
                  'ADD HOBBY'/>
              <ListView style={styles.container}
                    dataSource={this.state.dataSource}
                    renderRow={
                        this.renderRow}
          />

          </View>
      )
  }
}

class DeleteScreen extends React.Component{
    static navigationOptions ={
        title: 'Delete Hobby?',
    }
    constructor(props){
        super(props);
        const rowData = this.props.navigation.state.params.row;
        this.state ={ text: rowData.text, hours: rowData.hours }
    }
    _yes(){
        var newDs = [];
        for(var i=0; i < rows.length; i ++){
            if(rows[i].text == this.state.text)
                continue;
            rows[i].id = newDs.length;
            newDs.push(rows[i]);
        }
        rows = newDs;
        const {navigate} = this.props.navigation;
        navigate('Browse')
    }
    _no(){
        const {navigate} = this.props.navigation;
        navigate('Browse')
    }

    render() {
        return (
            <View style={styles.container}>
                <Text>Hobby name: {this.state.text}</Text>
                <Text>Number Of hours invested: {this.state.hours}</Text>
                <Button onPress={() => this._yes()} title=
                    'YES'/>
                <Button onPress={() => this._no()} title=
                    'NO'/>
            </View>
        );
    }
}

class AddScreen extends React.Component{
    static navigationOptions ={
        title: 'Add Hobby',
    }
    constructor(props){
        super(props);
        this.state ={ text: 'NULL', hours : 0}
    }

    _onPress() {
        const {navigate} = this.props.navigation;
        rows.push({id: rows.length, text: this.state.text, hours: this.state.hours})
        navigate('Browse')
    }

    _onPressShare() {
        Linking.openURL(
            'mailto:petean.mihai232@yahoo.com?subject=abcdefg&body=' +
            this.state.text)
        const {navigate} = this.props.navigation;
        navigate('Browse')
    }

    render(){
        return (
            <View style={styles.container}>
                <Text>Hobby name:</Text>
                <TextInput
                    style=
                        {
                            {height: 40, borderColor: 'gray', borderWidth: 1}
                        } value={this.state.text} onChangeText=
                        {
                            (text) => this.setState({text})
                        }
                />
                <Text>Number Of hours invested:</Text>
                <TextInput
                    style=
                        {
                            {height: 40, borderColor: 'gray', borderWidth: 1}
                        } value={this.state.hours} onChangeText=
                        {
                            (hours) => this.setState({hours})
                        }
                />
                <Button onPress={() => this._onPress()} title=
                    'SAVE'/>
                <Button onPress={() => this._onPressShare()} title=
                    'SHARE'/>
            </View>
        );
    }
}

class EditScreen extends React.Component{
	static navigationOptions ={
		title: 'Edit Hobby',
	}
	constructor(props){
		super(props);
		const rowData = this.props.navigation.state.params.row;
		this.state ={ text: rowData.text, cr: rowData.cr }
	}

	_onPress(rowData){
          // const { navigate } = this.props.navigation;
          var newDs = [];
          newDs = rows.slice();
          newDs[rowData.id].text = this.state.text;
          newDs[rowData.id].hours = this.state.hours;
        const {navigate} = this.props.navigation;
        navigate('Browse')

    }

    _onPressShare() {
        Linking.openURL(
            'mailto:petean.mihai232@yahoo.com?subject=abcdefg&body=' +
            this.state.text)
        const {navigate} = this.props.navigation;
        navigate('Browse')
    }

    render() {
        const rowData = this.props.navigation.state.params.row;
        return (
            <View style={styles.container}>
                <Text>Hobby name:</Text>
                <TextInput
                    style=
                        {
                            {height: 40, borderColor: 'gray', borderWidth: 1}
                        } value={this.state.text} onChangeText=
                        {
                            (text) => this.setState({text})
                        }
                />
                <Text>Number Of hours invested:</Text>
                <TextInput
                    style=
                        {
                            {height: 40, borderColor: 'gray', borderWidth: 1}
                        } value={this.state.hours} onChangeText=
                        {
                            (hours) => this.setState({hours})
                        }
                />
                <Button onPress={() => this._onPress(rowData)} title=
                    'SAVE'/>
                <Button onPress={() => this._onPressShare()} title=
                    'SHARE'/>
            </View>
        );
    }
}

class BrowseScreen extends React.Component {
    static navigationOptions = {
        title: 'Browse Screen',
    }

    render() {
        return (
            <View style={styles.container}>
                <Text>Hobby List:</Text>
                <HobbyList></HobbyList>
            </View>
        );
    }
}

export const RootNavigator = StackNavigator({
	Browse: { screen: HobbyList },
	Edit: { screen: EditScreen },
    Add: { screen: AddScreen},
    Delete: {screen: DeleteScreen},
})
 /*
                        export default class App extends React.Component {

	render(){
		return(
			<View style={styles.container}>
				<BrowseScreen/>
			</View>
		)
	}
}
*/
export default RootNavigator;
const styles = StyleSheet.create({
    container: {
        flex: 1,
        paddingTop: 22
    },
    row: {
        padding: 15,
        marginBottom: 5,
        backgroundColor: 'skyblue',
    },
});
